package swexpert.동선추적;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

class UserSolution {
    HashMap<Integer, Place> places;
    HashMap<Integer, ArrayList<Place>> users;

    HashMap<Integer, TreeSet<Place>> rowInfos;
    HashMap<Integer, TreeSet<Place>> colInfos;
    HashMap<Integer, TreeSet<Place>> rightUpInfos;
    HashMap<Integer, TreeSet<Place>> leftUpInfos;

    void init() {
        places = new HashMap<>();
        users = new HashMap<>();

        rowInfos = new HashMap<>();
        colInfos = new HashMap<>();
        rightUpInfos = new HashMap<>();
        leftUpInfos = new HashMap<>();
    }

    void addPlace(int pID, int r, int c) {
        Place place = new Place(pID, r, c);

        places.put(pID, place);

        if (!rowInfos.containsKey(r)) {
            rowInfos.put(r, new TreeSet<Place>(new Comparator<Place>() {
                @Override
                public int compare(Place o1, Place o2) {
                    return o1.c - o2.c;
                }
            }));
        }
        rowInfos.get(r).add(place);

        if (!colInfos.containsKey(c)) {
            colInfos.put(c, new TreeSet<Place>(new Comparator<Place>() {
                @Override
                public int compare(Place o1, Place o2) {
                    return o1.r - o2.r;
                }
            }));
        }
        colInfos.get(c).add(place);

        if (!rightUpInfos.containsKey(r + c)) {
            rightUpInfos.put(r + c, new TreeSet<Place>(new Comparator<Place>() {
                @Override
                public int compare(Place o1, Place o2) {
                    return o1.c - o2.c;
                }
            }));
        }
        rightUpInfos.get(r + c).add(place);

        if (!leftUpInfos.containsKey(r - c)) {
            leftUpInfos.put(r - c, new TreeSet<Place>(new Comparator<Place>() {
                @Override
                public int compare(Place o1, Place o2) {
                    return o1.c - o2.c;
                }
            }));
        }
        leftUpInfos.get(r - c).add(place);
    }

    void removePlace(int pID) {
        Place place = places.get(pID);
        place.delete();
    }

    void contactTracing(int uID, int visitNum, int moveInfo[], int visitList[]) {
        ArrayList<Place> visitPlaces = new ArrayList<>();

        Place current = places.get(moveInfo[0]);
        Place next = null;
        int direction = 0;
        visitList[0] = current.pID;
        visitPlaces.add(current);

        for (int i = 1; i <= visitNum - 1; i++) {
            direction = moveInfo[i];
            int nextPid = 0;
            TreeSet<Place> treeSet;
            switch (direction) {
                // 위쪽 방향
                case 0:
                    treeSet = colInfos.get(current.c);
                    next = treeSet.lower(current);

                    while (!next.isVisitable()) {
                        next = treeSet.lower(next);
                    }

                    break;
                case 1:
                    // 오른쪽위
                    treeSet = rightUpInfos.get(current.r + current.c);
                    next = treeSet.higher(current);

                    while (!next.isVisitable()) {
                        next = treeSet.higher(next);
                    }
                    break;
                case 2:
                    // 오른쪽
                    treeSet = rowInfos.get(current.r);
                    next = treeSet.higher(current);

                    while (!next.isVisitable()) {
                        next = treeSet.higher(next);
                    }
                    break;
                case 3:
                    treeSet = leftUpInfos.get(current.r - current.c);
                    next = treeSet.higher(current);

                    while (!next.isVisitable()) {
                        next = treeSet.higher(next);
                    }
                    break;
                case 4:
                    treeSet = colInfos.get(current.c);
                    next = treeSet.higher(current);

                    while (!next.isVisitable()) {
                        next = treeSet.higher(next);
                    }
                    break;
                case 5:
                    treeSet = rightUpInfos.get(current.r + current.c);
                    next = treeSet.lower(current);

                    while (!next.isVisitable()) {
                        next = treeSet.lower(next);
                    }
                    break;
                case 6:
                    treeSet = rowInfos.get(current.r);
                    next = treeSet.lower(current);

                    while (!next.isVisitable()) {
                        next = treeSet.lower(next);
                    }
                    break;
                case 7:
                    treeSet = leftUpInfos.get(current.r - current.c);
                    next = treeSet.lower(current);

                    while (!next.isVisitable()) {
                        next = treeSet.lower(next);
                    }

            }
            current = next;
            visitList[i] = current.pID;
            visitPlaces.add(current);
        }

        users.put(uID, visitPlaces);
        for (Place place :
                visitPlaces) {
            place.visit();
        }
    }

    void disinfectPlaces(int uID) {
        ArrayList<Place> visitPlaces = users.get(uID);

        // 모든 방문지들을 방문가능하게 표시해줌.
        for (Place place :
                visitPlaces) {
            place.disinfect();
        }
    }

    static class Place {

        int pID, r, c;
        boolean canVisit;
        boolean deleted;

        public Place(int pID, int r, int c) {
            this.pID = pID;
            this.r = r;
            this.c = c;
            this.canVisit = true;
            this.deleted = false;
        }

        public void visit() {
            canVisit = false;
        }

        public void delete() {
            deleted = true;
        }

        public void disinfect() {
            canVisit = true;
        }

        public boolean isVisitable() {
            return !deleted && canVisit;
        }
    }
}
