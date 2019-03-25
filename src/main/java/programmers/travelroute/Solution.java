package programmers.travelroute;

import java.util.Queue;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedList;

public class Solution {
    public String[] solution(String[][] tickets) {

        LinkedList<String[]> ticketsList = new LinkedList<String[]>();
        
        String[] answer;
        HashMap<String, Queue<String>> cities = new HashMap<String, Queue<String>>();
        for(String[] ticket : tickets){
            cities.putIfAbsent(ticket[0], new LinkedList<String>());
            cities.get(ticket[0]).add(ticket[1]);
            ticketsList.add(ticket);
        }
        
        LinkedList<String> temp = find("ICN", ticketsList, 1, tickets.length);
        Collections.reverse(temp);
        answer = temp.toArray(new String[temp.size()]);
        
        return answer;
    }
    public LinkedList<String> find(
        String destination, 
        LinkedList<String[]> tickets,
        int cnt, int remain){
        
        LinkedList<String> temp = new LinkedList<String>();
        LinkedList<String> visited = new LinkedList<String>();
        for(String[] ticket : tickets){
            if(ticket[0].equals(destination)){
                System.out.println(" start : " + destination+" destination : " + ticket[1]);

                LinkedList<String[]> tempTickets = new LinkedList<String[]>(tickets);
                tempTickets.remove(ticket);
                
                temp = find(ticket[1], tempTickets, cnt+1, remain-1 );
    
                System.out.println(cnt + " " + remain + " " + temp);
    
                if(temp.size() == remain){
                    
                    if(visited.isEmpty()){
                        visited = temp;
                    }else{
                        if(temp.getLast().compareTo(visited.getLast()) < 0 ){
                            visited = temp;
                        }
                    }
                }
            }
        }

        
        visited.add(destination);
        return visited;
    }
}