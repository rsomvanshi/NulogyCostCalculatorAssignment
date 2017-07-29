package com.nulogy.assignment;

import java.util.HashMap;
import java.util.Map;

import com.nulogy.assignment.util.Utils;

/*
 * Created by rsomvanshi on 07/29/2017
 */

public class NupackCostEstimator {

    private float baseValue;
    private int noOfPersons;
    private String materialType;

    private final float PER_PERSON_MARKUP_PERCENTAGE = 1.2f;
    private final float FLAT_MARKUP_PERCENTAGE = 5f;
    private static Map<String, Float> materialBasedMarkupPercentage = new HashMap<String, Float>();
    static {
        materialBasedMarkupPercentage.put("pharmaceuticals", 7.5f);
        materialBasedMarkupPercentage.put("food", 13f);
        materialBasedMarkupPercentage.put("electronics", 2f);
        materialBasedMarkupPercentage.put("miscellaneous", 0f);
    };

    private static Map<String, String> materialCategories = new HashMap<String, String>();
    static {
        materialCategories.put("drugs", "pharmaceuticals");
        materialCategories.put("food", "food");
        materialCategories.put("books", "miscellaneous");
    };

    public NupackCostEstimator(float baseValue, int noOfPersons, String materialType) {
        this.baseValue = baseValue;
        this.noOfPersons = noOfPersons;
        this.materialType = materialType;
    }

    public float getCostAfterFlatMarkup() {
        return baseValue += baseValue * (FLAT_MARKUP_PERCENTAGE / 100);
    }

    public float getCostAfterTotalMarkup() {

        String materialCategory = materialCategories.get(materialType.toLowerCase());
        float materialMarkup = materialBasedMarkupPercentage.get(materialCategory);
        float costAfterFlatMarkup = getCostAfterFlatMarkup();

        float totalMarkup = PER_PERSON_MARKUP_PERCENTAGE * noOfPersons + materialMarkup;
        float costAfterTotalMarkup = costAfterFlatMarkup + costAfterFlatMarkup * (totalMarkup / 100);

        return Utils.roundOffFloat(costAfterTotalMarkup);
    }
}
