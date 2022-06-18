package com.abdullah.vanhack;

import java.util.*;

/**
 * User: Syed Abdullah
 * Date: 16-Jun-2022
 * Time: 6:20 PM
 */
public class ChefScheduler {
    public static boolean schedulable(HashMap<String, ArrayList<String>> requests) {
        Set<String> days = new HashSet<String>(Set.of("sun", "mon", "tue", "wed", "thu", "fri", "sat"));
        List<String> persons = requests.keySet().stream().toList();
        Map<String, List<String>> schedule = new HashMap<>();
        days.forEach(day -> schedule.put(day, new ArrayList<>()));

        for (String day : days) {
            int totalPersonForDay = 0;
            for (int i = 0; i < persons.size() && totalPersonForDay < 2; i++) {
                schedule.get(day).add(persons.get(i));
                totalPersonForDay++;
                if (!isDayFallsOnRequestedHoliday(persons.get(i), day, requests) || !isScheduleValidTillNow(persons.get(i), schedule)) {
                    schedule.get(day).remove(persons.get(i));
                    totalPersonForDay--;
                }
            }
        }
        if (isScheduleInComplete(schedule)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isScheduleInComplete(Map<String, List<String>> schedule) {
        for (String day : schedule.keySet()) {
            return schedule.get(day).size() != 2;
        }
        return false;
    }

    public static boolean isScheduleValidTillNow(final String person, final Map<String, List<String>> schedule) {
        if (getTotalWorkingDaysOfPerson(person, schedule) > 5) {
            return false;
        }else {
            return true;
        }
    }

    public static int getTotalWorkingDaysOfPerson(final String person, final Map<String, List<String>> schedule) {
        return schedule.keySet().stream().mapToInt(day -> Collections.frequency(schedule.get(day), person)).sum();
    }

    public static boolean isDayFallsOnRequestedHoliday(final String person, final String day, final HashMap<String, ArrayList<String>> requests) {
        return requests.get(person).contains(day);
    }


    public static void itShouldWorkOnBasicExamples() {
        HashMap<String, ArrayList<String>> requests = new HashMap<>();
        requests.put(
                "ada", new ArrayList<>(Arrays.asList(
                        new String[]{"mon", "tue", "wed", "fri", "sat", "sun"}
                ))
        );
        requests.put(
                "emma", new ArrayList<>(Arrays.asList(
                        new String[]{"tue", "wed", "thu", "sun"}
                ))
        );
        requests.put(
                "remy", new ArrayList<>(Arrays.asList(
                        new String[]{"mon", "sat"}
                ))
        );
        requests.put(
                "zach", new ArrayList<>(Arrays.asList(new String[]{}))
        );

        System.out.println("it should work on a basic example. "+ true + ":"+ ChefScheduler.schedulable(requests));
        /*assertEquals(
                "it should work on a basic example.",
                true, ChefScheduler.schedulable(requests)
        );*/

        requests.clear();
        requests.put(
                "emma", new ArrayList<>(Arrays.asList(
                        new String[]{"sun"}
                ))
        );
        requests.put(
                "remy", new ArrayList<>(Arrays.asList(
                        new String[]{"sun"}
                ))
        );
        requests.put(
                "zach", new ArrayList<>(Arrays.asList(new String[]{}))
        );

        System.out.println("it should handle an example where there isn't enough coverage on a day. "+ false + ":"+ ChefScheduler.schedulable(requests));
        /*assertEquals(
                "it should handle an example where there isn't enough coverage on a day.",
                false, ChefScheduler.schedulable(requests)
        );*/

        requests.clear();
        requests.put(
                "ada", new ArrayList<>(Arrays.asList(
                        new String[]{"mon", "tue", "wed", "thu", "fri", "sat"}
                ))
        );
        requests.put(
                "emma", new ArrayList<>(Arrays.asList(
                        new String[]{"tue", "wed", "thu", "sun"}
                ))
        );
        requests.put(
                "remy", new ArrayList<>(Arrays.asList(
                        new String[]{"mon", "fri", "sun"}
                ))
        );
        requests.put(
                "zach", new ArrayList<>(Arrays.asList(new String[]{}))
        );

        System.out.println("it should handle an example where an employee would need to work in excess of 5 days. "+ false + ":"+ ChefScheduler.schedulable(requests));
        /*assertEquals(
                "it should handle an example where an employee would need to work in excess of 5 days.",
                false, ChefScheduler.schedulable(requests)
        );*/
    }

    public static void main(String[] args) {
        itShouldWorkOnBasicExamples();
    }

}
