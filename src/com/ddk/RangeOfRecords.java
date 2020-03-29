package com.ddk;

import com.ddk.common.Address;
import com.ddk.common.Demo;

import java.util.ArrayList;
import java.util.List;

public class RangeOfRecords {
    class ReturnObject {
        Integer startIdx;
        Integer endIdx;
        Boolean hasMore;
        Integer remainingBuckets;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Start Index: " + startIdx);
            sb.append("\nEnd Index: " + endIdx);
            sb.append("\nHasMore? " + hasMore);
            sb.append("\nRemaining Bucket " + remainingBuckets);
            return sb.toString();
        }
    }

    List<Demo> demos;
    public RangeOfRecords() {
        demos = new ArrayList<>();
        for ( int i = 0 ; i < 10 ; i++ ) {
            String suffix = String.format("%04d", i);
            Demo demo = new Demo();
            demo.name = "Full Name " + i;
            demo.age = i + 40;
            demo.address = new Address();
            demo.address.address1 = "Street " + suffix;
            demo.address.address1 = "Address 2 " + suffix;
            demo.address.city = "City " + suffix;
            demo.address.state = "State " + suffix;
            demo.address.country = "USA";
            demo.address.zip = 10000 + i;
            demos.add(demo);
        }
    }

    public List<ReturnObject> getSubRecords(int startIdx, int limit) {
        List<ReturnObject> returnObjects = new ArrayList<>();
        if ( limit <= 0 )
            limit = 1;
        if ( startIdx >= 0 ) {
            for ( int i = startIdx ; i < demos.size() ; i++ ) {
                ReturnObject returnObject = new ReturnObject();
                returnObject.startIdx = i;
                returnObject.endIdx = i + limit - 1;
                if (returnObject.endIdx > demos.size() - 1) {
                    returnObject.endIdx = demos.size() - 1;
                    returnObject.hasMore = false;
                    returnObject.remainingBuckets = 0;
                } else {
                    returnObject.hasMore = true;
                    returnObject.remainingBuckets = new Double(Math.ceil((demos.size() - i) / limit)).intValue();
                }
                returnObjects.add(returnObject);
                i = returnObject.endIdx + 1;
            }
        }
        return returnObjects;
    }

    public static void main(String[] args) {
        RangeOfRecords records = new RangeOfRecords();
        List<ReturnObject> returnObjects = records.getSubRecords(2, 0 );
        for ( ReturnObject returnObject : returnObjects ) {
            System.out.println(returnObject);
            for ( int i = returnObject.startIdx ; i <= returnObject.endIdx ; i++ )
                System.out.println(records.demos.get(i));
            System.out.println("=================");
        }
    }
}
