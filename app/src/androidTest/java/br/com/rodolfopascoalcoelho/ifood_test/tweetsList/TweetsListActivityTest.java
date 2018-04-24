package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TweetsListActivityTest {
    String userName = "userName";

    @Rule
    public ActivityTestRule<TweetsListActivity> tweetsListActivityActivityTestRule = new ActivityTestRule<>(TweetsListActivity.class);

    @Mock
    TweetsListContracts.TweetsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @UiThreadTest @Test
    public void searchUser() {
        assertNotNull(tweetsListActivityActivityTestRule.getActivity().presenter);

        boolean isNotValid = presenter.validateFields(tweetsListActivityActivityTestRule.getActivity().text_user_name);
        assertEquals(false, isNotValid);

        tweetsListActivityActivityTestRule.getActivity().text_user_name.setText(userName);

        boolean isValid = tweetsListActivityActivityTestRule.getActivity().presenter.validateFields(tweetsListActivityActivityTestRule.getActivity().text_user_name);
        assertEquals(true, isValid);
    }
}