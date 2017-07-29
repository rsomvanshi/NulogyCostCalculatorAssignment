package com.nulogy.assignment;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.nulogy.assignment.util.Utils;

/**
 * @author rsomvanshi
 *
 */
public class NupackCostEstimator {

    private float baseValue;
    private int noOfPersons;
    private String materialType;

    // Load configurable markup percentage values
    private static Map<String, Float> markupPercentageMap = new HashMap<String, Float>();
    static {
        Properties properties = new Properties();
        try {
            properties.load(NupackCostEstimator.class.getClassLoader().getResourceAsStream("markup-percentage.properties"));
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
        for (final Entry<Object, Object> entry : properties.entrySet()) {
            markupPercentageMap.put((String) entry.getKey(), Float.valueOf((String)entry.getValue()));
        }
    };

    // Load configurable material types and categories
    private static Map<String, String> materialCategoryMap = new HashMap<String, String>();
    static {
        Properties properties = new Properties();
        try {
            properties.load(NupackCostEstimator.class.getClassLoader().getResourceAsStream("material-categories.properties"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (final Entry<Object, Object> entry : properties.entrySet()) {
            materialCategoryMap.put((String) entry.getKey(), (String)entry.getValue());
        }
    };

    public NupackCostEstimator(float baseValue, int noOfPersons, String materialType) {
        this.baseValue = baseValue;
        this.noOfPersons = noOfPersons;
        this.materialType = materialType;
    }

    /** Get cost after applying flat markup percentage
     * @return float
     * @throws Exception
     */
    public float getCostAfterFlatMarkup() throws Exception {
        Float flatMarkupPercentage = markupPercentageMap.get("FLAT_MARKUP");
        if (flatMarkupPercentage != null) {
            return baseValue += baseValue * (flatMarkupPercentage / 100);
        }
        else {
            throw new Exception("Flat Markup percentage is not defined in the config file.");
        }
    }

    /** Get cost after applying all the markup percentages
     * @return float
     * @throws Exception
     */
    public float getCostAfterTotalMarkup() throws Exception {
        String materialCategory = materialCategoryMap.get(materialType.toUpperCase());
        float totalMarkup, costAfterTotalMarkup;

        if (materialCategory == null) {
            materialCategory = "MISCELLANEOUS";
        }
        Float materialMarkupPercentage = markupPercentageMap.get(materialCategory);

        if (materialMarkupPercentage != null) {
            Float perPersonMarkupPercentage = markupPercentageMap.get("PER_PERSON_MARKUP");

            if (perPersonMarkupPercentage != null) {
                float costAfterFlatMarkup = getCostAfterFlatMarkup();
                totalMarkup = perPersonMarkupPercentage * noOfPersons + materialMarkupPercentage;
                costAfterTotalMarkup = costAfterFlatMarkup + costAfterFlatMarkup * (totalMarkup / 100);
            }
            else {
                throw new Exception("Per person markup percentage is not defined in the config file.");
            }
        }
        else {
            throw new Exception("Per person markup percentage is not defined in the config file.");
        }

        return Utils.roundOffFloat(costAfterTotalMarkup);
    }
}
