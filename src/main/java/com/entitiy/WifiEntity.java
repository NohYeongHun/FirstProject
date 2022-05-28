package com.entitiy;

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
public class WifiEntity {
	int id;
	String mgrNo;
	String wrdofc;
	String wifiName;
	String streetAddress;
	String detailAddress;
	String installFloor;
	String installType;
	String installAgency;
	String serviceType;
	String netType;
	String installYear;
	String connectEnviron;
	String inOut;
	double lat;
	double lnt;
	String workDate;	
	
}
