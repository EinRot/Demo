package com.thread.countdown.json;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CommTest {

    public static void main(String[] args) throws JsonProcessingException {
        String msg = "{\"abnormalState\":\"\",\"aerocraftNo\":\"B1650\",\"agency\":\"215\",\"aibt\":\"\",\"airlineFull\":\"SYX-SZX\",\"airlineFullInfo\":\"{\\\"aRPT\\\":[{\\\"aPAT\\\":\\\"2403\\\",\\\"aPCD\\\":\\\"SYX\\\",\\\"aPNO\\\":\\\"1\\\",\\\"fELT\\\":\\\"\\\",\\\"fETT\\\":\\\"\\\",\\\"fPLT\\\":\\\"\\\",\\\"fPTT\\\":\\\"20190819204500\\\",\\\"fRLT\\\":\\\"\\\",\\\"fRTT\\\":\\\"\\\"},{\\\"aPAT\\\":\\\"2403\\\",\\\"aPCD\\\":\\\"SZX\\\",\\\"aPNO\\\":\\\"2\\\",\\\"fELT\\\":\\\"\\\",\\\"fETT\\\":\\\"\\\",\\\"fPLT\\\":\\\"20190819222000\\\",\\\"fPTT\\\":\\\"\\\",\\\"fRLT\\\":\\\"\\\",\\\"fRTT\\\":\\\"\\\"}]}\",\"airlineShort\":\"SYX-SZX\",\"airlinesTwocharcode\":\"CZ\",\"aldt\":\"\",\"aobt\":\"\",\"asrt\":\"\",\"associatedFlightId\":\"8330193\",\"atot\":\"\",\"attribute\":\"2403\",\"cobt\":\"\",\"codt\":\"\",\"craftType\":\"A321\",\"ctot\":\"\",\"ddtm\":\"20190819191316\",\"dfdl\":\"20190819002549\",\"eldt\":\"2019-08-19T13:59:00\",\"eobt\":\"2019-08-19T12:45:00\",\"execDate\":\"20190819\",\"flightId\":\"8330192\",\"flightNo\":\"6757\",\"fltExceptionReason\":\"\",\"fltExceptionReason2\":\"\",\"fltExceptionStatus\":\"\",\"fltExceptionStatus2\":\"\",\"fltNormalStatus\":\"\",\"fltNormalStatus2\":\"\",\"gateCode\":\"\",\"heading\":\"\",\"inseattime\":\"\",\"isOffIn\":\"A\",\"isVip\":0,\"isVirtual\":0,\"ista\":\"\",\"mist\":\"\",\"poa\":\"ZGSZ\",\"poa3\":\"SZX\",\"pod\":\"ZJSY\",\"pod3\":\"SYX\",\"pushtime\":\"\",\"route\":\"WL G221 NYB V12 AGPOR W71 LH H81 GYA A599 POU W7 SAREX W6 NLG W509 KEVAR\",\"startAlterateTakeoffTime\":\"\",\"startRealTakeoffTime\":\"\",\"startSchemeTakeoffTime\":\"20190819204500\",\"startStationThreecharcode\":\"SYX\",\"task\":\"W/Z\",\"terminalAlterateLandinTime\":\"\",\"terminalRealLandinTime\":\"\",\"terminalSchemeLandinTime\":\"20190819222000\",\"terminalStationThreecharcode\":\"SZX\",\"tobt\":\"\"}";
        FlightDynamic flightDynamic = JacksonMapper.instance.objectMapper.readValue(msg, FlightDynamic.class);
        System.out.println(flightDynamic.toString());
    }


}
