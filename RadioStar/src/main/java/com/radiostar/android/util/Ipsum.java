package com.radiostar.android.util;

/**
 * Created by Adnaan Velji on 12/26/13.
 */
public class Ipsum {

    private static String[] Titles = {
            "How Apple Created The Lightest iPad Yet",
            "A Mathematician Finds The Formula For The Perfect Pizza Pie"
    };

    private static String[] Descriptions = {
            "When Apple introduced the first iPad with Retina Display in March of last year, it didn't come without a cost. The third–gen iPad was the first Apple product to actually get fatter than its predecessor. Compared to the iPad 2, at 1.44 pounds, the iPad with Retina Display was about 50 grams heavier and oh–so–slightly thicker around the middle.\n\nFor a company that has always emphasized its ability to make each successive generation of its products lighter and thinner, the iPad with Retina Display was a real porker. The culprit? The Retina Display, and the 40% larger battery Apple needed to build into the iPad to power it.\n\nFirst announced with the iPhone 4, Apple's Retina Displays are screens with pixels so small that they can not be individually seen by the human eye (when held at an average viewing distance). For the iPad, a Retina Display is made up of 2048 x 1536 ultra–tiny pixels, four times as many as a non–Retina iPad. Retina Displays are easier to read and more lifelike than regular displays. But those extra pixels really drain the battery.",
            "There's a formula for pi, so why not for pie (pizza pie)? Pizza––of which the round kind has an area of pi times radius squared––is among the world's most beloved foods. Yet what constitutes pizza differs greatly and passionately from region to region, city to city, corner to cornern.\n\nIn Naples, the historic birthplace of the flatbread foodstuff, locals prefer a minimal version cooked quickly in hellish temperatures that yields a soft, springy crust (or cornicione) and a wet center. New York, pizza's first outpost in the New World, developed a taste for the coal oven, thin–crust kind. (You'd have to stack at least five of these up to equal a deep–dish Chicago rendition.) In Scotland, they still deep–fry it in plenty of chip shops. As pizza picked up in Asia, toppings got adventurous, from calamari and sweet potato to shrimp and, yes, mayo.\n\nEverything about the food, down to its most basic components, seems up for grabs. Even so, one mathematician, Dr. Eugenia Cheng, has developed a formula for the perfect pie, an outstanding claim that could have far–reaching consequences for big pizza business and everyday enthusiasts alike. It all comes down to the crust, she tells Co.Design."};

    public static String getTitle(int index) {
        try {
            return Titles[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    public static String[] getTitles() {
        return Titles;
    }

    public static String getDescription(int index) {
        try {
            return Descriptions[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    public static String[] getDescriptions() {
        return Descriptions;
    }

}
