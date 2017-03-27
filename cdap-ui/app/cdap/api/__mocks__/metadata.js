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

/*
  TODO: This is just a stub(mock) for jest to not invoke the actual socket connection.
  This needs to be exported as a singleton class. Will do when we actually need to mock a function.
*/
const getMetadata = () => {};
const getProperties = () => {};
const addProperties = () => {};
const deleteProperty = () => {};
const getTags = () => {};
const addTags = () => {};
const deleteTags = () => {};

export default {
  getMetadata,
  getProperties,
  addProperties,
  deleteProperty,
  getTags,
  addTags,
  deleteTags
};