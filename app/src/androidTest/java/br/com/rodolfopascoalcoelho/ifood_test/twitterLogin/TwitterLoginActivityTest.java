package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TwitterLoginActivityTest {

    @Rule
    public ActivityTestRule<TwitterLoginActivity> mActivityTestRule = new ActivityTestRule<>(TwitterLoginActivity.class);


    @Test
    public void onCreate() {
        assertNotNull(mActivityTestRule.getActivity().presenter);
    }

}
