package poten012.sonmong.Poten403.domain.aiconsulting.dto.response;

import lombok.Builder;

@Builder
public record CurationResponseVo(
        String title,
        String content
) {
    public static CurationResponseVo of(String title, String content) {
        return CurationResponseVo.builder()
                .title(title)
                .content(content)
                .build();
    }
}
