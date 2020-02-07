package com.kdj.ndatacenter.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@lombok.Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data {
	@NonNull private String period;
	@NonNull private String ratio;
}
