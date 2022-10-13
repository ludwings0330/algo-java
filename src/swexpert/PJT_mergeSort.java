package swexpert;

import java.util.HashMap;

public class PJT_mergeSort {
    static Location[] gyms;
    static Location[] apts;

    static HashMap<Location, Integer> cache;

    static Location[] gymScores;
    static Location[] sorted;

    public static void main(String[] args) {
        init();

        getScores();

        System.out.println("============ 정렬 전 ==============");
        for (var apt :
                apts) {
            System.out.print("apt.name = " + apt.name);
            System.out.println(700 - apt.score + "m 거리에 헬스장");
        }

        mergeSort(gymScores, 0, gymScores.length - 1);
        System.out.println("============ 정렬 후 ==============");

        for (var apt :
                gymScores) {
            System.out.print("apt.name = " + apt.name + " ");
            System.out.println(700 - apt.score + "m 거리에 헬스장");
        }
    }

    private static void mergeSort(Location[] gymScores, int left, int right) {
        if (left == right)
            return;

        int mid = (left + right) / 2;

        mergeSort(gymScores, left, mid);
        mergeSort(gymScores, mid + 1, right);

        merge(gymScores, left, mid, right);
    }

    private static void merge(Location[] gymScores, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            // 내림차순 정렬
            if (gymScores[l].score >= gymScores[r].score) {
                sorted[idx++] = gymScores[l++];
            } else {
                sorted[idx++] = gymScores[r++];
            }
        }

        while (r <= right)
            sorted[idx++] = gymScores[r++];

        while (l <= mid)
            sorted[idx++] = gymScores[l++];

        for (int i = left; i <= right; i++)
            gymScores[i] = sorted[i];
    }

    private static void getScores() {
        int i = 0;
        for (Location apt : apts) {
            int cnt = 0;
            int minDist = Integer.MAX_VALUE;
            if (!cache.containsKey(apt)) {
                for (Location gym : gyms) {
                    double dist = getDist(apt, gym);
                    minDist = Math.min(minDist, (int) dist);
                    if (dist <= 150)
                        cnt++;
                }

                int score = Math.max(0, 700 - minDist) + cnt * 30;
                apt.score = score;

                cache.put(apt, score);
            } else {
                apt.score = cache.get(apt);
            }


            gymScores[i++] = apt;
        }
    }

    private static double getDist(Location apt, Location gym) {
        double theta = apt.lng - gym.lng;
        double dist = Math.sin(degToRad(apt.lat)) * Math.sin(degToRad(gym.lat)) +
                Math.cos(degToRad(apt.lat)) * Math.cos(degToRad(gym.lat)) * Math.cos(degToRad(theta));
        dist = Math.acos(dist);
        dist = radToDeg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;

        return dist;
    }

    private static double radToDeg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private static double degToRad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static void init() {
        int N = 15;
        int M = 15;

        apts = new Location[N];
        gymScores = new Location[N];
        sorted = new Location[N];

        cache = new HashMap<>();
        gyms = new Location[M];
        apts[0] = new Location(0, "개나리래미안아파트", 37.49856978131905, 127.04642014109963);
        apts[1] = new Location(1, "역삼래미안아파트", 37.49751592497375, 127.05004728330309);
        apts[2] = new Location(2, "디오빌역삼아파트", 37.50151920789805, 127.04288897591145);
        apts[3] = new Location(3, "테헤란아이파크아파트", 37.50250161866783, 127.04680709272424);
        apts[4] = new Location(4, "역삼푸르지오아파트", 37.49775512904686, 127.04678151274898);
        apts[5] = new Location(5, "역삼자이아파트", 37.500386994160536, 127.04437885710304);
        apts[6] = new Location(6, "강남센트럴아이파크아파트", 37.50038649492938, 127.04568844126749);
        apts[7] = new Location(7, "역삼e편한세상아파트", 37.49877740616262, 127.04987623178826);
        apts[8] = new Location(8, "역삼1차아이파크아파트", 37.499316059682535, 127.0457918305614);
        apts[9] = new Location(9, "한화진넥스빌아파트", 37.50195479579008, 127.04424181209724);
        apts[10] = new Location(10, "래미안펜타빌아파트", 37.500699316696824, 127.04753427154562);
        apts[11] = new Location(11, "역삼래미안그레이튼1단지아파트", 37.49516364933424, 127.04724579539071);
        apts[12] = new Location(12, "경남아파트", 37.500547188941106, 127.04242814185429);
        apts[13] = new Location(13, "쌍용플래티넘밸류아파트", 37.49539984153658, 127.03040567183359);
        apts[14] = new Location(14, "개나리푸르지오아파트", 37.49972161637075, 127.0499809018625);

        gyms[0] = new Location(0, "애니타임피트니스 역삼점", 37.4965234769632, 127.039296820812);
        gyms[1] = new Location(1, "화이트짐 역삼점", 37.4937932274632, 127.03989019243);
        gyms[2] = new Location(2, "짐다운짐", 37.4984376879894, 127.031727762728);
        gyms[3] = new Location(3, "스포애니 선릉역점", 37.505277919481706, 127.04829266614371);
        gyms[4] = new Location(4, "F45트레이닝 역삼", 37.4985435120446, 127.033519112236);
        gyms[5] = new Location(5, "스포애니 역삼동점", 37.4928881110161, 127.041359736627);
        gyms[6] = new Location(6, "스포애니 역삼역점", 37.4999899680186, 127.035437758429);
        gyms[7] = new Location(7, "헬스보이짐 강남역점", 37.4964902401947, 127.029695944353);
        gyms[8] = new Location(8, "미진휘트니스센타", 37.4978510190592, 127.028647041621);
        gyms[9] = new Location(9, "하와이짐", 37.4980713432367, 127.03948535584);
        gyms[10] = new Location(10, "휘슬러휘트니스", 37.4909413875926, 127.031439640281);
        gyms[11] = new Location(11, "헬스보이짐 선릉점", 37.5039724705748, 127.045758483763);
        gyms[12] = new Location(12, "저스트짐", 37.49308863013239, 127.03417253366577);
        gyms[13] = new Location(13, "스포애니 강남역1호점", 37.4946647022403, 127.030084222137);
        gyms[14] = new Location(14, "휘트니스클리닉 역삼점", 37.4950805464733, 127.037819211275);

    }

    static class Location {
        Integer id;
        String name;
        Double lat;
        Double lng;
        int score;

        public Location(int id, String name, Double lat, Double lng) {
            this.id = id;
            this.name = name;
            this.lat = lat;
            this.lng = lng;
            this.score = 0;
        }

        @Override
        public boolean equals(Object obj) {
            return this.lat.equals(((Location) obj).lat) && this.lng.equals(((Location) obj).lng);
        }

        @Override
        public int hashCode() {
            return lat.intValue() * lng.intValue();
        }
    }
}
