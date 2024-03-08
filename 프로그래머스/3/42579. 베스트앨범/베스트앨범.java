import java.util.*;

class Solution {    
    static class Song implements Comparable<Song> {
        int no, playCnt;
        
        Song(int no, int playCnt) {
            this.no = no;
            this.playCnt = playCnt;
        }
        
        @Override
        public int compareTo(Song o) {
            if (this.playCnt == o.playCnt) {
                return this.no - o.no;
            }
            return o.playCnt - this.playCnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        List<Integer> answerNos = new ArrayList<>();
        Map<String, List<Song>> map = new HashMap<>();
        Map<String, Integer> playCntMap = new HashMap<>();
        List<Map.Entry<String, Integer>> list;
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCnt = plays[i];
            
            if (!map.containsKey(genre)) {
                map.put(genre, new ArrayList<>());
            }
            List<Song> songs = map.get(genre);
            songs.add(new Song(i, playCnt));
            
            playCntMap.put(genre, playCntMap.getOrDefault(genre, 0) + playCnt);
        }
        
        list = new ArrayList<>(playCntMap.entrySet());
        Collections.sort(list, Map.Entry.comparingByValue());
        
        for (int idx = list.size() - 1; idx >= 0; idx--) {
            Map.Entry<String, Integer> entry = list.get(idx);
            List<Song> selectedSongs = map.get(entry.getKey());
            Collections.sort(selectedSongs);
            int cnt = 0;
            for (int i = 0; i < selectedSongs.size(); i++) {
                Song song = selectedSongs.get(i);
                answerNos.add(song.no);
                cnt++;
                if (cnt == 2) {
                    break;
                }
            }
        }
        
        answer = answerNos.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}