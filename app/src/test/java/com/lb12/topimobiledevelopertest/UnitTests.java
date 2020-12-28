package com.lb12.topimobiledevelopertest;

import junit.framework.TestCase;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.lb12.topimobiledevelopertest.util.Utils.addIfNotEmptyOrNull;
import static com.lb12.topimobiledevelopertest.util.Utils.extractYoutubeId;

public class UnitTests extends TestCase {

    @Test
    public void testExtractYoutubeId() throws MalformedURLException {

        String url = "https://www.youtube.com/watch?v=bzJDimvPW1Y";

        String youtubeID = extractYoutubeId( url );
        assertEquals("bzJDimvPW1Y", youtubeID );

    }

    @Test
    public void testaddIfNotEmptyOrNull(){

        ArrayList<String> ingredientsList = new ArrayList<String>();

        addIfNotEmptyOrNull(ingredientsList, "ingredient1");
        addIfNotEmptyOrNull(ingredientsList, null);
        addIfNotEmptyOrNull(ingredientsList, "ingredient2");
        addIfNotEmptyOrNull(ingredientsList, "");
        addIfNotEmptyOrNull(ingredientsList, "     ");
        addIfNotEmptyOrNull(ingredientsList, "ingredient3");

        assertEquals(3,ingredientsList.size());
    }



}
