// You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//
// What is the maximum number of envelopes can you Russian doll? (put one inside other)
//
// Example:
// Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).



class Solution {
public:
    int maxEnvelopes(vector<pair<int, int>>& envelopes) {
        if (envelopes.empty()) return 0;
        sort(envelopes.begin(), envelopes.end(), [](pair<int, int>& e1, pair<int, int>& e2){
            if (e1.first == e2.first) return e1.second < e2.second;
            return e1.first < e2.first;
        });
        vector<int> dp(envelopes.size());
        int ret = 1;
        for (int i = 0; i < dp.size(); ++i) {  // longest increasing sequence
            dp[i] = 1;  // if none of previous doll can be put in, current doll itself count 1
            for (int j = 0; j < i; ++j) {
                if (envelopes[j].first < envelopes[i].first && envelopes[j].second < envelopes[i].second) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            ret = max(ret, dp[i]);
        }
        return ret;
    }

   // nlog(n), make elements in the LIS as small as possible, especially the last one which is the gate to control the size LIS
   // first sort the array via the width in ascending order and then sort the sub-array with the same width in
   // descending order (the height) then the two conditions in LIS will also be met traversing from the smallest
   // width to the biggest: and the height will be used as that in LIS - the last element will be updated to be as
   // smaller as possible and meantime maintain the envelopes constraint since its width order will always be valid,
   // furthermore the condition 2 is also met just as that in LIS.
   int maxEnvelopes2(vector<pair<int, int>>& envelopes) {
	     int size = envelopes.size();
	     sort(envelopes.begin(), envelopes.end(), [](pair<int, int> a, pair<int, int>b){
		       return a.first<b.first || (a.first==b.first && a.second>b.second);
	     });
	     vector<int> lis;
	     for(auto& pair: envelopes) {
           // lower_bound: first element >= val, upper_bound: first element > val
		       auto iter = lower_bound(lis.begin(), lis.end(), pair.second);
		       if(iter == lis.end()) lis.push_back(pair.second);
		       else if(*iter > pair.second) *iter = pair.second;
	     }
	     return lis.size();
   }
};
