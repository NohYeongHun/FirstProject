package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WifiDto {
	WifiObject TbPublicWifiInfo;
	static final int Length = 1000;
	
	public WifiDto createWifiObj(int start, int end) throws IOException {
		
		String result = new ParsingData().getWifiInfo(start, end);
		WifiDto wifiObj = getWifiObj(result);
		return wifiObj;
	}
	
	public int getTotalPage(WifiDto wifiObj) {
		return Integer.valueOf(wifiObj.getTbPublicWifiInfo().getList_total_count());
	}
	
	public List<Integer> getPageList(int totalPage){
		List<Integer> list = new ArrayList<Integer>();
		int pageCnt = (int)Math.ceil((double)totalPage / 1000);
		for (int i = 0; i < pageCnt - 1; i++) {
			list.add(1000);
		}
		int lastVal = totalPage % Length == 0 ? 1000 : totalPage % 1000;
		list.add(lastVal);
		return list;
		
	}
	
	private WifiDto getWifiObj(String result) {
		// LAT와 LNT 위치 바뀜
		Gson gson = new Gson();
		WifiDto wifiObj = gson.fromJson(result, WifiDto.class);
		return wifiObj;
	}
}
