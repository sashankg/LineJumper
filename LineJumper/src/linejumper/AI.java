/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linejumper;

import java.util.ArrayList;

/**
 *
 * @author Sashank
 */
public class AI {

    private ArrayList<ArrayList<ArrayList<String>>> fringe;
    private ArrayList<ArrayList<String>> tested;

    public AI(ArrayList<String> s) {
        fringe = new ArrayList<>();
        ArrayList<ArrayList<String>> temp = new ArrayList<>(); 
        temp.add(s);
        fringe.add(temp);
        tested = new ArrayList<>();
        tested.add(s);
    }

    public ArrayList<Integer> solution() {
        ArrayList<Integer> output = new ArrayList<>();
        while (!didWin(fringe.get(0).get(0))) { 
            ArrayList<ArrayList<String>> pop = fringe.remove(0); 
            for (int i = 0; i < 5; i++) {
                ArrayList<ArrayList<String>> temp = new ArrayList<>(pop);
                ArrayList<String> step = new ArrayList<>(temp.get(0));

                int ind = step.indexOf("_");
                if (ind + (2 - i) >= 0 && ind + (2 - i) < step.size()) {
                    step.set(ind, step.get(ind + (2 - i)));
                    step.set(ind + (2 - i), "_");
                    temp.add(0, step);
                    if (!doesContain(tested, step)) {
                        fringe.add(temp);
                        tested.add(step);
                    }
                }
            }
        }

        output = convertToSteps(fringe.get(0));
        return output;
    }

    public static boolean didWin(ArrayList<String> arr) {
        ArrayList<String> win = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            win.add("F");
        }
        win.add("_");
        for (int i = 0; i < 4; i++) {
            win.add("T");
        }
        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).equals(win.get(i))) {
                return false;

            }
        }
        return true;

    }

    public static boolean doesContain(ArrayList<ArrayList<String>> ar1, ArrayList<String> ar2) {
        for (int i = 0; i < ar1.size(); i++) {
            if (ar1.get(i).equals(ar2)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Integer> convertToSteps(ArrayList<ArrayList<String>> ar) {
        ArrayList<Integer> output = new ArrayList<>();
        for (ArrayList<String> n : ar) {
            output.add(0, n.indexOf("_"));
        }
        return output;
    }

}
