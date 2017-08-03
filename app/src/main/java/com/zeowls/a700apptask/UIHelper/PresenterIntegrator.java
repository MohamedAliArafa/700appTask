package com.zeowls.a700apptask.UIHelper;

import com.zeowls.a700apptask.Fragments.StoreFragment;

/**
 * Created by root on 8/2/17.
 */

public interface PresenterIntegrator {
    void loadStoreData(StoreFragment storeFragment);
    void rxUnSubscribe();
}
