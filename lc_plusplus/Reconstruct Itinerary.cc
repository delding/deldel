// Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
// Note:
// If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
// All airports are represented by three capital letters (IATA code).
// You may assume all tickets form at least one valid itinerary.
// Example 1:
// tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
// Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
// Example 2:
// tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
// Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.


class Solution {
public:
    vector<string> findItinerary(vector<pair<string, string>> tickets) {
        int N = tickets.size() + 1;
        unordered_map<string, vector<string>> adjs;
        for (auto& t : tickets) {
            adjs[t.first].push_back(t.second);
        }
        for (auto& e : adjs) {
            sort(e.second.begin(), e.second.end());
        }
        vector<string> ret;
        find(ret, adjs, "JFK", N);
        return ret;
    }

    bool find(vector<string>& ret, unordered_map<string, vector<string>>& adjs, const string& city, int N) {
        ret.push_back(city);
        if (ret.size() == N) return true;
        auto& neibors = adjs[city];
        int size = neibors.size();
        for (int i = 0; i < size; ++i) {
            auto nextCity = neibors[i];
            neibors.erase(neibors.begin() + i);
            if (find(ret, adjs, nextCity, N)) return true;
            neibors.insert(neibors.begin() + i, nextCity);
        }
        ret.pop_back();
        return false;
    }
};
