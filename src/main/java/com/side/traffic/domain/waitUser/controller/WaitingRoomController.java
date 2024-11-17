package com.side.traffic.domain.waitUser.controller;

import com.side.traffic.domain.waitUser.service.UserQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class WaitingRoomController {
    private final UserQueueService userQueueService;

    @GetMapping("/waiting-room")
    String waitingRoomPage(@RequestParam(name = "queue", defaultValue = "default") String queue,
                           @RequestParam(name = "user_id") Long userId,
                           Model model) {

        Boolean check = userQueueService.isAllowed(queue, userId).block();


        if (check) {
            return "waiting-main-room";
        }

        Mono<Long> mono = userQueueService.registerWaitQueue(queue, userId)
                .onErrorResume(ex -> userQueueService.getRank(queue, userId));

        model.addAttribute("queue", queue);
        model.addAttribute("userId", userId);
        model.addAttribute("rank", mono.block());

        return "waiting-room";
    }
}
