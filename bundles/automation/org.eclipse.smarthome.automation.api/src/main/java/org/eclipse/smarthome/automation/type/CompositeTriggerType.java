/**
 * Copyright (c) 1997, 2015 by ProSyst Software GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.automation.type;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.automation.Trigger;
import org.eclipse.smarthome.automation.Visibility;
import org.eclipse.smarthome.config.core.ConfigDescriptionParameter;

/**
 * {@code CompositeTriggerType} is as {@link TriggerType} which logically combines {@link Trigger} modules. The
 * composite trigger hides internal logic between participating {@link Trigger}s and it can be used as a regular
 * {@link Trigger} module.
 *
 * @author Yordan Mihaylov - Initial Contribution
 * @author Ana Dimova - Initial Contribution
 * @author Vasil Ilchev - Initial Contribution
 */
@NonNullByDefault
public class CompositeTriggerType extends TriggerType {

    private final List<Trigger> children;

    /**
     * This constructor is responsible for creation of a {@code CompositeTriggerType} with ordered set of
     * {@link Trigger}s. It initialize only base properties of the {@code CompositeTriggerType}.
     *
     * @param UID the {@link TriggerType}'s identifier, or {@code null} if a random identifier should be generated.
     * @param configDescriptions describing metadata for the configuration of the future {@link Trigger} instances.
     * @param outputs a {@link List} with {@link Output} meta-information descriptions of the future {@link Trigger}
     *            instances.
     * @param children is a {@link LinkedHashSet} of {@link Trigger}s.
     *
     */
    public CompositeTriggerType(@Nullable String UID, @Nullable List<ConfigDescriptionParameter> configDescriptions,
            @Nullable List<Output> outputs, @Nullable List<Trigger> children) {
        super(UID, configDescriptions, outputs);
        this.children = children != null ? Collections.unmodifiableList(children) : Collections.emptyList();
    }

    /**
     * This constructor is responsible for creation of a {@code CompositeTriggerType} with ordered set of
     * {@link Trigger}s. It initialize all properties of the {@code CompositeTriggerType}.
     *
     * @param UID the {@link TriggerType}'s identifier, or {@code null} if a random identifier should be generated.
     * @param configDescriptions describing metadata for the configuration of the future {@link Trigger} instances.
     * @param label a short and accurate, human-readable label of the {@link TriggerType}.
     * @param description a detailed, human-readable description of usage of {@link TriggerType} and its benefits.
     * @param tags defines categories that fit the {@link TriggerType} and which can serve as criteria for searching
     *            or filtering it.
     * @param visibility determines whether the {@link TriggerType} can be used by anyone if it is
     *            {@link Visibility#VISIBLE} or only by its creator if it is {@link Visibility#HIDDEN}.
     * @param outputs a {@link List} with {@link Output} meta-information descriptions of the future {@link Trigger}
     *            instances.
     * @param children is a {@link LinkedHashSet} of {@link Trigger}s.
     */
    public CompositeTriggerType(@Nullable String UID, @Nullable List<ConfigDescriptionParameter> configDescriptions,
            @Nullable String label, @Nullable String description, @Nullable Set<String> tags,
            @Nullable Visibility visibility, @Nullable List<Output> outputs, @Nullable List<Trigger> children) {
        super(UID, configDescriptions, label, description, tags, visibility, outputs);
        this.children = children != null ? Collections.unmodifiableList(children) : Collections.emptyList();
    }

    /**
     * This method is used to obtain the {@link Trigger}s of the {@code CompositeTriggerType}.
     *
     * @return a {@link LinkedHashSet} of the {@link Trigger} modules of this {@code CompositeTriggerType}.
     */
    public List<Trigger> getChildren() {
        return children;
    }

}
