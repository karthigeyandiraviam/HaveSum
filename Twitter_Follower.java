import java.io.*;
import java.util.*;

/* 
Your previous Python 3 content is preserved below:

# Assume a mini Twitter-like service with N users, each of which
# has a user ID of integer, and can follow zero or more users.
# (But a user cannot follow itself.)
#
# A statement "A user X is reachable from a user Y" is true if
# - Y follows X, or
# - Y follows U1, who follows X, or
# - Y follows U1, who follows U2, who follows X, or
# - ...
#
# For example, with the "follow" relationship below,
# we can say "5 is reachable from 1" because
# 1 follows 3, who follows 4, who follows 5.
#
# 1 follows 2 and 3
# 2 follows nobody
# 3 follows 1 and 4
# 4 follows 5
#
#
# Question: implement a function `is_reachable_from(user_a, user_b)`
# 

# The dictionary below does not have an entry for user 2,
# which means user 2 follows nobody.
followees = {
    1: [2, 3], # user 1 follows user 2 and user 3.
    3: [1, 4], # user 3 follows user 1 and user 4.
    4: [5],    # user 4 follows user 5.
}

def is_reachable_from(user_a, user_b):
    """
    Return True if "user_a is reachable from user_b", and False otherwise.
    """
    pass

 */

class Solution {
    Map<Integer, List<Integer>> followers;
    Set<Integer> visited;
    Boolean isFollowed;

    public Solution() {
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
          
            // If follower is not found
            // Recursivedly call findFollower for each user in list
            if ( ! isFollowed ) {
                for (Integer f : followers.get(src)) {
                    findFollower(f, dst);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution followers = new Solution();
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

        followers = new Solution();
        followers.add(10, new ArrayList<Integer>() {{ add(11); add(12); }});
        followers.add(11, new ArrayList<Integer>() {{ add(10); add(12); }});
        followers.add(12, new ArrayList<Integer>() {{ add(10); add(11); }});
        followers.printFollowers();

        // Test - 10 is reachable from 11 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(11, 10) == true );
        // Test - 10 is reachable from 11 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(10, 11) == true );
        // Test - 10 is reachable from 12 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(12, 10) == true );
        // Test - 12 is reachable from 10 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(10, 12) == true );
        // Test - 11 is reachable from 12 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(11, 12) == true );
        // Test - 12 is reachable from 11 - should return true - expected is true - Should print true
        System.out.println( followers.is_reachable_from(12, 11) == true );
    }
}


