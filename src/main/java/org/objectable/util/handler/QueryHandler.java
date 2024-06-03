package org.objectable.util.handler;

import org.objectable.configuration.Config;
import org.objectable.model.model.record.QueryRecord;
import org.objectable.model.model.record.WaitingTimelineRecord;
import org.objectable.util.logging.Logger;

import java.util.List;

public class QueryHandler {


    public void executeQuery(QueryRecord queryRecord, List<WaitingTimelineRecord> waitingTimelineRecords) {
        List<WaitingTimelineRecord> matchedRecords = waitingTimelineRecords
                .stream()
                .filter(waitingTimelineRecord -> waitingTimelineRecord.matches(queryRecord))
                .toList();
        long averageWaitingTime = Math.round(matchedRecords
                .stream()
                .mapToInt(WaitingTimelineRecord::getWaitingTime)
                .summaryStatistics()
                .getAverage());
        Logger.pure(String.format("%s",
                (matchedRecords.isEmpty() || averageWaitingTime == 0 ?
                        Config.getProperty("OUTPUT_NOT_DEFINED_PLACEHOLDER") : averageWaitingTime)));
    }
}
