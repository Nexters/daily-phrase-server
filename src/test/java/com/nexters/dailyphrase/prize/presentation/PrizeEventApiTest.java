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
import com.nexters.dailyphrase.common.enums.PrizeTicketSource;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.prize.domain.*;
import com.nexters.dailyphrase.prize.domain.repository.*;

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
    @Autowired private PrizeEntryCheckRepository prizeEntryCheckRepository;

    private MockMvc mockMvc;

    Long eventId;
    int prizeCount = 5;
    private List<Prize> prizes;
    @Autowired private PrizeTicketRepository prizeTicketRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        PrizeEvent prizeEvent =
                PrizeEvent.builder()
                        .name("Sample Event")
                        .eventMonth(8)
                        .startAt(LocalDateTime.now().minusDays(1))
                        .endAt(LocalDateTime.now().plusDays(1))
                        .winnerAnnouncementAt(LocalDateTime.now().plusDays(2))
                        .status(PrizeEventStatus.ACTIVE)
                        .build();
        eventId = prizeEventRepository.save(prizeEvent).getId(); // 경품 이벤트 저장

        prizes =
                IntStream.range(0, prizeCount)
                        .mapToObj(
                                i ->
                                        Prize.builder()
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
        PrizeEntry prizeEntry = PrizeEntry.builder().memberId(1L).prize(prizes.get(0)).build();
        prizeEntryRepository.save(prizeEntry);

        PrizeEntry prizeEntry2 = PrizeEntry.builder().memberId(1L).prize(prizes.get(0)).build();
        prizeEntryRepository.save(prizeEntry2);

        PrizeTicket ticket =
                PrizeTicket.builder()
                        .memberId(1L)
                        .status(PrizeTicketStatus.AVAILABLE)
                        .source(PrizeTicketSource.SHARE)
                        .build();
        prizeTicketRepository.save(ticket);

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
                .andExpect(jsonPath("$.result.prizeList[0].myEntryCount").value(2))
                .andExpect(jsonPath("$.result.myTicketCount").value(1));
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
                        .memberId(1L)
                        .prize(prize)
                        .phoneNumber("010-1234-5678")
                        .status(PrizeEntryStatus.WINNING)
                        .build();
        prizeEntryRepository.save(prizeEntry);

        PrizeEntryCheck prizeEntryCheck =
                PrizeEntryCheck.builder().prizeId(prizeId).memberId(1L).build();
        prizeEntryCheckRepository.save(prizeEntryCheck);

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/events/prizes")
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.result.prizeList[0].prizeEntryResult.status").value("WINNING"))
                .andExpect(
                        jsonPath("$.result.prizeList[0].prizeEntryResult.isChecked").value(true));
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
                        .memberId(1L)
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
        for (int i = 1; i <= 10; i++) {
            PrizeTicket prizeTicket =
                    PrizeTicket.builder()
                            .id(Long.valueOf(i))
                            .eventId(eventId)
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

    @Test
    @DisplayName("회원가입 티켓 획득 후 팝업 렌더링 플래그 확인 테스트입니다.")
    @WithMockUser(username = "1")
    void 회원가입_티켓_획득_후_팝업() throws Exception {
        // given
        prizeTicketRepository.save(
                PrizeTicket.builder()
                        .eventId(eventId)
                        .memberId(1L)
                        .status(PrizeTicketStatus.AVAILABLE)
                        .source(PrizeTicketSource.SIGNUP)
                        .build());

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/events/tickets/me");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.showGetTicketPopup").value(Boolean.TRUE));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.showGetTicketPopup").value(Boolean.FALSE));
    }

    @Test
    @DisplayName("회원가입 미획득시 팝업 렌더링 플래그 확인 테스트입니다.")
    @WithMockUser(username = "1")
    void 회원가입_미획득시_팝업() throws Exception {
        // given

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/events/tickets/me");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.showGetTicketPopup").value(Boolean.FALSE));
    }

    @Test
    @DisplayName("당첨자 전화번호 입력 테스트입니다. - 잘못된 형식")
    @WithMockUser(username = "1")
    void 당첨자_전화번호_입력() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("phoneNumber", "01012341234"); // 잘못된 형식 (정규식에 맞지 않음)
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        Prize prize = prizes.get(0);
        PrizeEntry prizeEntry =
                PrizeEntry.builder()
                        .prize(prize)
                        .status(PrizeEntryStatus.WINNING)
                        .memberId(1L)
                        .build();
        prizeEntryRepository.save(prizeEntry);

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post(
                                "/api/v1/events/prizes/" + prize.getId() + "/phone-number")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest()); // HTTP 상태 코드가 400인지 확인
    }

    @Test
    @DisplayName("당첨자 전화번호 입력 테스트입니다. - 올바른 형식")
    @WithMockUser(username = "1")
    void 당첨자_전화번호_입력_성공() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("phoneNumber", "010-1234-1234");
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        Prize prize = prizes.get(0);
        PrizeEntry prizeEntry =
                PrizeEntry.builder()
                        .prize(prize)
                        .status(PrizeEntryStatus.WINNING)
                        .memberId(1L)
                        .build();
        prizeEntryRepository.save(prizeEntry);

        // when & then
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post(
                                "/api/v1/events/prizes/" + prize.getId() + "/phone-number")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.phoneNumber").value("010-1234-1234"));
    }
}
