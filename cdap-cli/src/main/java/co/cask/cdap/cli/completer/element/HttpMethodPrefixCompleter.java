/*
 * Copyright © 2014 Cask Data, Inc.
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
 * the License
 */

package co.cask.cdap.cli.completer.element;

import co.cask.cdap.api.service.http.ServiceHttpEndpoint;
import co.cask.cdap.client.ServiceClient;
import co.cask.cdap.client.exception.NotFoundException;
import co.cask.cdap.client.exception.UnAuthorizedAccessTokenException;
import co.cask.common.cli.completers.PrefixCompleter;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Prefix completer for Http methods.
 */
public class HttpMethodPrefixCompleter extends PrefixCompleter {

  private static final String REGEX = "^call service ('.+?'|\".+?\"|\\S+) ";
  private static final int PROGRAM_ID_START = 13;

  private final ServiceClient serviceClient;
  private final EndpointCompleter completer;

  public HttpMethodPrefixCompleter(final ServiceClient serviceClient, String prefix, EndpointCompleter completer) {
    super(prefix, completer);
    this.serviceClient = serviceClient;
    this.completer = completer;
  }

  @Override
  public int complete(String buffer, int cursor, List<CharSequence> candidates) {
    //TODO: add util for getting programId or another param from user input based on prefix.
    if (buffer != null) {
      Pattern pattern = Pattern.compile(REGEX);
      Matcher matcher = pattern.matcher(buffer);
      if (matcher.find()) {
        int end = matcher.end() - 1;
        String programId = buffer.substring(PROGRAM_ID_START, end);
        String[] appAndServiceIds = programId.split("\\.");
        completer.setEndpoints(getMethods(appAndServiceIds[0], appAndServiceIds[1]));
      }
    }
    return super.complete(buffer, cursor, candidates);
  }

  public Collection<String> getMethods(String appId, String serviceId) {
    Collection<String> httpMethods = Lists.newArrayList();
    try {
      for (ServiceHttpEndpoint endpoint : serviceClient.getEndpoints(appId, serviceId)) {
        String method = endpoint.getMethod();
        if (!httpMethods.contains(method)) {
          httpMethods.add(method);
        }
      }
    } catch (IOException ignored) {
    } catch (UnAuthorizedAccessTokenException ignored) {
    } catch (NotFoundException ignored) {
    }
    return httpMethods;
  }
}
