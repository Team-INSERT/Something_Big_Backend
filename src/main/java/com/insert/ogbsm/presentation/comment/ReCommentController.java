package com.insert.ogbsm.presentation.comment;

import com.insert.ogbsm.global.security.util.SecurityUtil;
import com.insert.ogbsm.presentation.comment.dto.PageReCommentRes;
import com.insert.ogbsm.presentation.comment.dto.ReCommentReq;
import com.insert.ogbsm.service.comment.ReCommentDefService;
import com.insert.ogbsm.service.comment.ReCommentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recomment")
@RequiredArgsConstructor
public class ReCommentController {
    private final ReCommentDefService reCommentDefService;
    private final ReCommentReadService reCommentReadService;

    @PostMapping("/{commentId}")
    public void create(@RequestBody ReCommentReq reqDto, @PathVariable Long commentId) {
        Long userId = SecurityUtil.getCurrentUserIdWithLogin();
        reCommentDefService.create(reqDto, commentId, userId);
    }

    @GetMapping("/{commentId}")
    public PageReCommentRes read(@PathVariable Long commentId, @PageableDefault Pageable pageable) {
        return reCommentReadService.read(commentId, pageable);
    }

    @PutMapping()
    public void update(@RequestBody ReCommentReq reqDto) {
        Long userId = SecurityUtil.getCurrentUserIdWithLogin();
        reCommentDefService.update(reqDto, userId);
    }

    @DeleteMapping("/{reCommentId}")
    public void delete(@PathVariable Long reCommentId) {
        Long userId = SecurityUtil.getCurrentUserIdWithLogin();
        reCommentDefService.delete(reCommentId, userId);
    }
}