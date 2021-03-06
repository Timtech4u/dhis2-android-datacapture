/*
 * Copyright (c) 2015, University of Oslo
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.dhis2.mobile.sdk.controllers;

import org.dhis2.mobile.sdk.DhisManager;
import org.dhis2.mobile.sdk.entities.CategoryOption;
import org.dhis2.mobile.sdk.network.APIException;
import org.dhis2.mobile.sdk.network.tasks.GetCategoryOptionsTask;
import org.dhis2.mobile.sdk.persistence.models.Session;

import java.util.List;

public final class GetCategoryOptionsController implements IController<List<CategoryOption>> {
    private final DhisManager mDhisManager;
    // private final CategoryOptionHandler mCatOptionHandler;
    private final Session mSession;
    private final List<String> mCatOptionIds;

    public GetCategoryOptionsController(DhisManager manager,
                                        //CategoryOptionHandler handler,
                                        Session session, List<String> ids) {
        mDhisManager = manager;
        // mCatOptionHandler = handler;
        mSession = session;
        mCatOptionIds = ids;
    }

    @Override
    public List<CategoryOption> run() throws APIException {
        return (new GetCategoryOptionsTask(mDhisManager,
                mSession.getServerUri(), mSession.getCredentials(),
                mCatOptionIds)).run();
    }
        /*
        Map<String, CategoryOption> newBaseCatOptions = getNewBaseCatOptions();
        Map<String, CategoryOption> oldCatOptions = getOldFullCatOptions();

        List<String> catOptionsToDownload = new ArrayList<>();
        for (String newCatOptionKey : newBaseCatOptions.keySet()) {
            CategoryOption newCatOption = newBaseCatOptions.get(newCatOptionKey);
            CategoryOption oldCatOption = oldCatOptions.get(newCatOptionKey);

            if (oldCatOption == null) {
                catOptionsToDownload.add(newCatOptionKey);
                continue;
            }

            DateTime newLastUpdated = DateTime.parse(newCatOption.getLastUpdated());
            DateTime oldLastUpdated = DateTime.parse(oldCatOption.getLastUpdated());

            if (newLastUpdated.isAfter(oldLastUpdated)) {
                // we need to update current version
                catOptionsToDownload.add(newCatOptionKey);
            }
        }

        Map<String, CategoryOption> newCatOptions = getNewFullCatOptions(catOptionsToDownload);
        List<CategoryOption> combinedCatOptions = new ArrayList<>();
        for (String newCatOptionKey : newBaseCatOptions.keySet()) {
            CategoryOption newCatOption = newCatOptions.get(newCatOptionKey);
            CategoryOption oldCatOption = oldCatOptions.get(newCatOptionKey);

            if (newCatOption != null) {
                combinedCatOptions.add(newCatOption);
                continue;
            }

            if (oldCatOption != null) {
                combinedCatOptions.add(oldCatOption);
            }
        }
        return combinedCatOptions;
    }

    private Map<String, CategoryOption> getNewBaseCatOptions() throws APIException {
        return toMap(
                (new GetCategoryOptionsTask(mDhisManager,
                        mSession.getServerUri(), mSession.getCredentials(),
                        mCatOptionIds)).run()
        );
    }

    private Map<String, CategoryOption> getNewFullCatOptions(List<String> ids) throws APIException {
        return toMap(
                (new GetCategoryOptionsTask(mDhisManager,
                        mSession.getServerUri(), mSession.getCredentials(),
                        ids)).run()
        );
    }

    private Map<String, CategoryOption> getOldFullCatOptions() {
        return toMap(mCatOptionHandler.query());
    }

    */
}
