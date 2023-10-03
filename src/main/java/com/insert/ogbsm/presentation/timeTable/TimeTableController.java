package com.insert.ogbsm.presentation.timeTable;

import com.insert.ogbsm.domain.user.User;
import com.insert.ogbsm.infra.security.util.SecurityUtil;
import com.insert.ogbsm.presentation.timeTable.dto.TimeTableRes;
import com.insert.ogbsm.service.timeTable.TimeTableReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeTable")
public class TimeTableController {
    private final TimeTableReadService timeTableReadService;

    @GetMapping("/bar")
    public TimeTableRes getBar() {
        User user = SecurityUtil.getCurrentUserOrNotLogin();
        return timeTableReadService.getBarByGradeAndClass(user);
    }

    @GetMapping("/table")
    public TimeTableRes getTable() {
        User user = SecurityUtil.getCurrentUserOrNotLogin();
        return timeTableReadService.getTableByGradeAndClass(user);
    }
}
