package com.insert.ogbsm.domain.post;

import com.insert.ogbsm.domain.common.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PostBase extends BaseTime {

    private String title;

    private String content;

    // ToDo (생성자에도 추가해야함)
    // private User writer;

    public PostBase(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostBase update(String title, String content) {
        this.title = title;
        this.content = content;

        return this;
    }
}
