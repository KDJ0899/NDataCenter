package com.kdj.ndatacenter.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Results {
	
	@NonNull private String title;
	@NonNull private String[] keywords;
	@NonNull private Data data;
	
}
