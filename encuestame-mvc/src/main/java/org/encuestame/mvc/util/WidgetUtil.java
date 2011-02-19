/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.mvc.util;

import javax.servlet.http.HttpServletRequest;

import org.encuestame.core.files.PathUtil;
import org.encuestame.core.util.MD5Utils;

/**
 * Dojo Widget Utils.
 * @author Picado, Juan juanATencuestame.org
 * @since Jan 23, 2011 8:23:58 AM
 * @version $Id:$
 */
public class WidgetUtil {

    private static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";

    private static final String URL = "http://";

    private static final Integer REQUEST_SERVER_PORT = 80;

    /**
     *
     * @param request
     * @return
     */
    public static String getUserProfileImagePath(final HttpServletRequest request) {
        final StringBuilder domain = new StringBuilder(WidgetUtil.getDomain(request));
        domain.append(PathUtil.profileUserImage);
        return domain.toString();
    }

    /**
     * Get Domain.
     * @param request
     * @return
     */
    public static final String getDomain(final HttpServletRequest request) {
        final StringBuffer domain = new StringBuffer(WidgetUtil.URL);
        domain.append(request.getServerName());
        if (request.getServerPort() != WidgetUtil.REQUEST_SERVER_PORT) {
            domain.append(":");
            domain.append(request.getServerPort());
        }
        // buffer.append("//");
        domain.append(request.getContextPath());
        return domain.toString();
    }

    /**
     * Get Gravatar.
     * @param email
     * @param size
     * @return
     */
    public final String getGravatar(final String email, Integer size) {
        final String hash = MD5Utils.md5Hex(email);
        StringBuilder gravatarUl = new StringBuilder();
        gravatarUl.append(WidgetUtil.GRAVATAR_URL);
        gravatarUl.append(hash);
        gravatarUl.append("?s=");
        gravatarUl.append(size);
        return gravatarUl.toString();
    }
}
