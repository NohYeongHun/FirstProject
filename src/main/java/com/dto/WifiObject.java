package com.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WifiObject {
	String list_total_count;
	RequstStatus RESULT;
	List<WifiList> row = new ArrayList<WifiList>();
}
