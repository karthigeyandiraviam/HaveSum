package com.ddk;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiskSizeByFileType {
    static String solution(String S) {
        // write your code in Java SE 8
        LinkedHashMap<String,Integer> fileMap = new LinkedHashMap<String, Integer>() {{
            put("music", 0);
            put("images", 0);
            put("movies", 0);
            put("other", 0);
        }};

        if ( ! S.isEmpty() ) {
            String[] lines = S.split("\n");
            for (String l : lines) {
                String[] fn_sz = l.split(" ", 2);
                String ext = fn_sz[0].substring(fn_sz[0].lastIndexOf('.') + 1);
                Integer size = Integer.parseInt(fn_sz[1].substring(0, fn_sz[1].lastIndexOf('b')));
                String category = "other";
                if ("mp3".equals(ext) || "aac".equals(ext) || "flac".equals(ext))
                    category = "music";
                else if ("jpg".equals(ext) || "bmp".equals(ext) || "gif".equals(ext))
                    category = "images";
                else if ("mp4".equals(ext) || "avi".equals(ext) || "mkv".equals(ext))
                    category = "movies";
                fileMap.put(
                        category,
                        fileMap.get(category) + size
                );
            }
        }
        StringBuilder sb = new StringBuilder();
        for ( Map.Entry<String, Integer> entry : fileMap.entrySet() ) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("b\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        //System.out.println(solution("my.song.jpg 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b"));
        System.out.println(solution(""));
    }
}
