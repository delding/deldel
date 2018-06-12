// TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
//
// Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.


class Solution {
public:

    // Encodes a URL to a shortened URL.
    string encode(string longUrl) {
        auto h = hasher(longUrl);  // can be used as id in db
        string shortUrl;
        while (h != 0) {
            shortUrl += base[h % base.size()];
            h /= base.size();
        }
        shortToLong[shortUrl] = longUrl;
        return home + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    string decode(string shortUrl) {
        return shortToLong[shortUrl.substr(home.size())];
    }

private:
    unordered_map<string, string> shortToLong;
    const string base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    const string home = "http://tinyurl.com/";
    hash<string> hasher;
};

// Your Solution object will be instantiated and called as such:
// Solution solution;
// solution.decode(solution.encode(url));
