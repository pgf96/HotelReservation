package edu.wgu.d387_sample_code.convertor;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeServiceImpl implements TimeService {
    private Map<String, String> tzMap = new HashMap<String, String>();

    @Override
    public Map<String, String> getTimeZones() {
        ZonedDateTime zdt = ZonedDateTime.now().withHour(11).withMinute(30).withSecond(0).withNano(0);

        zdt.withHour(11).withMinute(30).withSecond(0);

        ZoneId est = ZoneId.of("America/New_York");
        ZoneId mst = ZoneId.of("America/Denver");
        ZoneId utc = ZoneId.of("UTC");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a z");
        zdt.withZoneSameInstant(est).format(formatter);

        tzMap.put("est", zdt.withZoneSameInstant(est).format(formatter));
        tzMap.put("mst", zdt.withZoneSameInstant(mst).format(formatter));
        tzMap.put("utc", zdt.withZoneSameInstant(utc).format(formatter));

        return this.tzMap;
    }
}
