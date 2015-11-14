/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 **/

public class Solution {
  public int findDuplicate(int[] nums) {
    // array nums[] is defined by a function f(i) = nums[i], domain and range are both [1, n]
    // since index 0 is not in [1, n], start position should be i = nums[0], no need to consider
    // the case of jumping back to index 0
    int n = nums.length;
    int slow = nums[0], fast = nums[0];
    do {
      slow = nums[slow];
      fast = nums[fast];
      fast = nums[fast];
    } while (slow != fast);
    fast = nums[0];
    while (fast != slow) {
      slow = nums[slow];
      fast = nums[fast];
    }
    return slow;
  }
}
/*
# This problem (reportedly) took CS legend Don Knuth twenty-four hours to solve
# and I have only met one person (Keith Amling) who could solve it in less time
# than this.
#
# The first part of this problem - proving that at least one duplicate element
# must exist - is a straightforward application of the pigeonhole principle.
# If the values range from 0 to n - 2, inclusive, then there are only n - 1
# different values.  If we have an array of n elements, one must necessarily be
# duplicated.
#
# The second part of this problem - finding the duplicated element subject to
# the given constraints - is much harder.  To solve this, we're going to need a
# series of nonobvious insights that transform the problem into an instance of
# something entirely different.
#
# The main trick we need to use to solve this problem is to notice that because
# we have an array of n elements ranging from 0 to n - 2, we can think of the
# array as defining a function f from the set {0, 1, ..., n - 1} onto itself.
# This function is defined by f(i) = A[i].  Given this setup, a duplicated
# value corresponds to a pair of indices i != j such that f(i) = f(j).  Our
# challenge, therefore, is to find this pair (i, j).  Once we have it, we can
# easily find the duplicated value by just picking f(i) = A[i].
#
# But how are we to find this repeated value?  It turns out that this is a
# well-studied problem in computer science called cycle detection.  The general
# form of the problem is as follows.  We are given a function f.  Define the
# sequence x_i as
#
#    x_0     = k       (for some k)
#    x_1     = f(x_0)
#    x_2     = f(f(x_0))
#    ...
#    x_{n+1} = f(x_n)
#
# Assuming that f maps from a domain into itself, this function will have one
# of three forms.  First, if the domain is infinite, then the sequence could be
# infinitely long and nonrepeating.  For example, the function f(n) = n + 1 on
# the integers has this property - no number is ever duplicated.  Second, the
# sequence could be a closed loop, which means that there is some i so that
# x_0 = x_i.  In this case, the sequence cycles through some fixed set of
# values indefinitely.  Finally, the sequence could be "rho-shaped."  In this
# case, the sequence looks something like this:
#
#     x_0 -> x_1 -> ... x_k -> x_{k+1} ... -> x_{k+j}
#                        ^                       |
#                        |                       |
#                        +-----------------------+
#
# That is, the sequence begins with a chain of elements that enters a cycle,
# then cycles around indefinitely.  We'll denote the first element of the cycle
# that is reached in the sequence the "entry" of the cycle.
#
# For our particular problem of finding a duplicated element in the array,
# consider the sequence formed by starting at position n - 1 and then
# repeatedly applying f.  That is, we start at the last position in the array,
# then go to the indicated index, repeating this process.  My claim is that
# this sequence is rho-shaped.  To see this, note that it must contains a cycle
# because the array is finite and after visiting n elements, we necessarily
# must visit some element twice.  This is true no matter where we start off in
# the array.  Moreover, note that since the array elements range from 0 to
# n - 2 inclusive, there is no array index that contains n - 1 as a value.
# Consequently, when we leave index n - 1 after applying the function f one
# time, we can never get back there.  This means that n - 1 can't be part of a
# cycle, but if we follow indices starting there we must eventually hit some
# other node twice.  The concatenation of the chain starting at n - 1 with the
# cycle it hits must be rho-shaped.
#
# Moreover, think about the node we encounter that starts at the entry of the
# cycle.  Since this node is at the entry of the cycle, there must be two
# inputs to the function f that both result in that index being generated.  For
# this to be possible, it must be that there are indices i != j with
# f(i) = f(j), meaning that A[i] = A[j].  Thus the index of the entry of the
# cycle must be one of the values that is duplicated in the array.
#
# There is a famous algorithm due to Robert Floyd that, given a rho-shaped
# sequence, finds the entry point of the cycle in linear time and using only
# constant space.  This algorithm is often referred to as the "tortoise and
# hare" algorithm
*/

