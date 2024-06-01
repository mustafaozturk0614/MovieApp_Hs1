package com.bilgeadam.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private Long id;
    private String email;
    private String message;
}
