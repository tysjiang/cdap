/*
 * Copyright © 2017 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import DataPrepStore from 'components/DataPrep/store';

export function directiveRequestBodyCreator(directivesArray, wsId) {
  let workspaceId = wsId || DataPrepStore.getState().dataprep.workspaceId;

  return {
    version: 1.0,
    workspace: {
      name: workspaceId,
      results: 100
    },
    recipe: {
      directives: directivesArray
    },
    sampling: {
      method: "FIRST",
      limit: 1000
    }
  };
}

export function isCustomOption(selectedOption) {
  return selectedOption.substr(0, 6) === 'CUSTOM';
}
