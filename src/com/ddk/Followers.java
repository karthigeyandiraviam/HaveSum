package com.ddk;

import java.util.*;

public class Followers {
    Map<Integer, List<Integer>> followers;
    Set<Integer> visited;
    Boolean isFollowed;

    public Followers() {
        followers = new HashMap<>();
    }

    // Add Followers to user
    public void add(Integer user, List<Integer> follower) {
        this.followers.put(user, follower);
    }

    // Print
    public void printFollowers() {
        for ( Map.Entry<Integer, List<Integer>> entry : this.followers.entrySet() )
            System.out.println(entry.getKey() + ": " + String.join(", ", entry.getValue().toString()));
    }

    // Is user_a reachable from user_b ?
    public boolean is_reachable_from(Integer user_a, Integer user_b) {
        visited = new HashSet<>(); // Initialize visited to avoid overflow of recursive calls
        isFollowed = false;
        if ( user_a == user_b ) // user cannot follow itself
            return false;
        findFollower(user_b, user_a);
        return isFollowed;
    }

    // Find Follower
    public void findFollower(Integer src, Integer dst) {
        // If src is in map and not visited before
        if ( followers.containsKey(src) && ! visited.contains(src) ) {
            for ( Integer f : followers.get(src) ) {
                // Check dst user is in list
                if ( f == dst ) {
                    isFollowed = true;
                    break;
                }
            }
            // Add src to visited list
            visited.add(src);

            if ( ! isFollowed ) {
                // Recursivedly call findFollower for each user in list
                for (Integer f : followers.get(src)) {
                    findFollower(f, dst);
                }
            }
        }
    }

    public static void main(String[] args) {
        Followers followers = new Followers();
        followers.add(1, new ArrayList<Integer>() {{ add(2); add(3); }});
        followers.add(3, new ArrayList<Integer>() {{ add(1); add(4); }});
        followers.add(4, new ArrayList<Integer>() {{ add(5); }});
        followers.printFollowers();

        // Test - 5 is reachable from 1 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(5, 1) == true );
        // Test - 1 is reachable from 1 should return false - expected is false - Should print true
        System.out.println( followers.is_reachable_from(1, 1) == false );
        // Test - 3 is reachable from 1 should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(1, 3) == true );
        // Test - 1 is reachable from 3 should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(3, 1) == true );
        // Test - 6 is reachable from 1 should return false - expected is false - Should print true
        System.out.println( followers.is_reachable_from(6, 1) == false );
    }
}
