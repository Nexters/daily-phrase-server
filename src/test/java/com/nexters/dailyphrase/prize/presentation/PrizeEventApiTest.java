package com.nexters.dailyphrase.prize.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import com.nexters.dailyphrase.common.enums.PrizeEventStatus;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.prize.domain.Prize;
import com.nexters.dailyphrase.prize.domain.PrizeEntry;
import com.nexters.dailyphrase.prize.domain.PrizeEvent;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.domain.repository.PrizeEntryRepository;
import com.nexters.dailyphrase.prize.domain.repository.PrizeEventRepository;
import com.nexters.dailyphrase.prize.domain.repository.PrizeRepository;
import com.nexters.dailyphrase.prize.domain.repository.PrizeTicketRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional // 각 테스트가 독립적인 트랜잭션 내에서 실행되도록 설정
class PrizeEventApiTest {
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private PrizeEventRepository prizeEventRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private PrizeEntryRepository prizeEntryRepository;
    @Autowired private PrizeRepository prizeRepository;

    private MockMvc mockMvc;

    Long eventId = 2L;
    int prizeCount = 5;
    private Member testMember;
    private List<Prize> prizes;
    @Autowired private PrizeTicketRepository prizeTicketRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        PrizeEvent prizeEvent =
                PrizeEvent.builder()
                        .id(eventId)
                        .name("Sample Event")
                        .startAt(LocalDateTime.now().minusDays(1))
                        .endAt(LocalDateTime.now().plusDays(1))
                        .winnerAnnouncementAt(LocalDateTime.now().plusDays(2))
                        .status(PrizeEventStatus.ACTIVE)
                        .build();
        prizeEventRepository.save(prizeEvent); // 경품 이벤트 저장

        testMember = Member.builder().id(1L).name("testuser").build();
        memberRepository.save(testMember); // 테스트 멤버 저장

        prizes =
                IntStream.range(0, prizeCount)
                        .mapToObj(
                                i ->
                                        Prize.builder()
                                                .id((long) i)
                                                .event(prizeEvent)
                                                .name("Prize " + i)
                                                .shortName("Short Prize" + i)
                                                .imageUrl("http://example.com/prize" + i + ".jpg")
                                                .bannerImageUrl("")
                                                .welcomeImageUrl("")
                                                .manufacturer("manufacturer" + i)
                                                .requiredTicketCount(5)
                                                .build())
                        .collect(Collectors.toList());
        prizeRepository.saveAll(prizes); // 경품들 저장
    }

    @Test
    @DisplayName("경품 목록 조회 기능 테스트입니다.")
    @WithMockUser(username = "1")
    void 경품_목록_조회_테스트() throws Exception {
        // 멤버가 일부 경품에 응모한 내역 생성
        PrizeEntry prizeEntry =
                PrizeEntry.builder().memberId(testMember.getId()).prize(prizes.get(0)).build();
        prizeEntryRepository.save(prizeEntry);

        PrizeEntry prizeEntry2 = PrizeEntry.builder().memberId(2L).prize(prizes.get(0)).build();
        prizeEntryRepository.save(prizeEntry2);

        System.out.println("PRIZES : " + prizes);

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/events/prizes")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.prizeList[0].prizeId").isNotEmpty())
                .andExpect(jsonPath("$.result.prizeList[0].name").value("Prize 0"))
                .andExpect(
                        jsonPath("$.result.prizeList[0].imageUrl")
                                .value("http://example.com/prize0.jpg"))
                .andExpect(jsonPath("$.result.prizeList[0].requiredTicketCount").value(0))
                .andExpect(jsonPath("$.result.prizeList[0].totalEntryCount").value(2))
                .andExpect(jsonPath("$.result.prizeList[0].myEntryCount").value(1)); // 응모한 내역 수 확인
    }

    @Test
    @DisplayName("경품 응모 결과 확인 기능 테스트입니다. - 당첨")
    @WithMockUser(username = "1")
    void 경품_응모_결과_확인_테스트_당첨() throws Exception {
        // given
        Prize prize = prizes.get(0);
        Long prizeId = prize.getId();

        PrizeEntry prizeEntry =
                PrizeEntry.builder()
                        .memberId(testMember.getId())
                        .prize(prize)
                        .status(PrizeEntryStatus.WINNING)
                        .build();
        prizeEntryRepository.save(prizeEntry);

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/events/prizes/{prizeId}/entry-result", prizeId)
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.status").value("WINNING"));
    }

    @Test
    @DisplayName("경품 응모 결과 확인 기능 테스트입니다. - 미당첨")
    @WithMockUser(username = "1")
    void 경품_응모_결과_확인_테스트_미당첨() throws Exception {
        // given
        Prize prize = prizes.get(0);
        Long prizeId = prize.getId();

        PrizeEntry prizeEntry =
                PrizeEntry.builder()
                        .memberId(testMember.getId())
                        .prize(prize)
                        .status(PrizeEntryStatus.MISSED)
                        .build();
        prizeEntryRepository.save(prizeEntry);

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/events/prizes/{prizeId}/entry-result", prizeId)
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.status").value("MISSED"));
    }

    @Test
    @DisplayName("경품 응모 기능 테스트입니다. - 응모권 부족 예외")
    @WithMockUser(username = "1")
    void 경품_응모_테스트_응모권_부족() throws Exception {
        // given
        Prize prize = prizes.get(0);
        Long prizeId = prize.getId();

        // when & then
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("prizeId", prizeId);
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/events/enter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("PRIZE_TICKET_400_1"));
    }

    @Test
    @DisplayName("경품 응모 결과 확인 처리 테스트입니다. - 응모 결과 확인 저장")
    @WithMockUser(username = "1")
    void 경품_응모_결과_확인_테스트() throws Exception {
        // given
        Prize prize = prizes.get(0);
        Long prizeId = prize.getId();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("prizeId", prizeId);
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/events/prizes/entry-result/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest);

        // when & then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.prizeId").value(prizeId))
                .andExpect(jsonPath("$.result.memberId").value(1L));
    }

    @Test
    @DisplayName("경품 응모 기능 테스트입니다. - 응모 성공")
    @WithMockUser(username = "1")
    void 경품_응모_테스트_응모_성공() throws Exception {
        // given
        Prize prize = prizes.get(0);
        Long prizeId = prize.getId();

        List<PrizeTicket> prizeTicketList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            PrizeTicket prizeTicket =
                    PrizeTicket.builder()
                            .id(Long.valueOf(i))
                            .eventId(1L)
                            .status(PrizeTicketStatus.AVAILABLE)
                            .memberId(1L)
                            .build();
            prizeTicketList.add(prizeTicket);
        }
        prizeTicketRepository.saveAll(prizeTicketList);

        // when & then
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("prizeId", prizeId);
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/events/enter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.prizeId").value(prizeId))
                .andExpect(jsonPath("$.result.memberId").value(1L))
                .andExpect(jsonPath("$.result.status").value(PrizeEntryStatus.ENTERED.toString()));
    }
}
