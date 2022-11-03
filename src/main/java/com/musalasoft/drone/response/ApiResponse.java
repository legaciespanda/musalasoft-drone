package com.musalasoft.drone.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private boolean responseStatus;
    private String responseMessage;
    private String responseCode;
}
