/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.automation.parser.gson.internal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.smarthome.automation.parser.ParsingException;
import org.eclipse.smarthome.automation.parser.ParsingNestedException;
import org.eclipse.smarthome.automation.template.RuleTemplate;
import org.eclipse.smarthome.automation.template.Template;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * This class can parse and serialize sets of {@link Template}s.
 *
 * @author Kai Kreuzer - Initial Contribution
 *
 */
public class TemplateGSONParser extends AbstractGSONParser<Template> {

    @Override
    public Set<Template> parse(InputStreamReader reader) throws ParsingException {
        JsonReader jr = new JsonReader(reader);
        try {
            if (jr.hasNext()) {
                JsonToken token = jr.peek();
                Set<Template> templates = new HashSet<>();
                if (JsonToken.BEGIN_ARRAY.equals(token)) {
                    templates.addAll(gson.fromJson(jr, new TypeToken<List<RuleTemplate>>() {
                    }.getType()));
                } else {
                    Template template = gson.fromJson(jr, RuleTemplate.class);
                    templates.add(template);
                }
                return templates;
            }
        } catch (Exception e) {
            throw new ParsingException(new ParsingNestedException(ParsingNestedException.TEMPLATE, null, e));
        } finally {
            try {
                jr.close();
            } catch (IOException e) {
            }
        }
        return Collections.emptySet();
    }
}
