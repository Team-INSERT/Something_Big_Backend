package com.insert.ogbsm.service.comment;

import com.insert.ogbsm.domain.comment.ReComment;
import com.insert.ogbsm.domain.comment.repo.ReCommentRepo;
import com.insert.ogbsm.domain.like.Likes;
import com.insert.ogbsm.domain.like.repo.LikesRepo;
import com.insert.ogbsm.domain.like.type.Type;
import com.insert.ogbsm.domain.user.repo.UserWrapper;
import com.insert.ogbsm.presentation.comment.dto.ReCommentRes;
import com.insert.ogbsm.presentation.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReCommentReadService {
    private final ReCommentRepo reCommentRepo;
    private final UserWrapper userWrapper;
    private final LikesRepo likesRepo;

    public Pagination<List<ReCommentRes>> read(Long commentId, Pageable pageable, Long userId) {

        Page<ReComment> pageReComment = reCommentRepo.findByCommentIdOrderByLikeCountDescCreatedAtAsc(commentId, pageable);
        List<ReCommentRes> reComments = pageReComment.stream()
                .map(reComment -> new ReCommentRes(
                                reComment,
                        userWrapper.getUser(reComment.getUserId()),
                        doesLikeComment(userId, reComment)
                        )
                )
                .collect(Collectors.toList());

        return new Pagination<>(reComments, pageReComment.getTotalPages(), pageable.getPageNumber());
    }

    private boolean doesLikeComment(Long userId, ReComment reComment) {
        if (userId != null) {
            Optional<Likes> likes = likesRepo.findByUserIdAndPartyIdAndType(userId, reComment.getId(), Type.RECOMMENT);
            return likes.isPresent();
        }

        return false;
    }
}
