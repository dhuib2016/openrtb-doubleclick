/*
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.doubleclick.openrtb;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.openrtb.OpenRtb.CreativeAttribute;

import java.util.Collection;

/**
 * Maps between AdX creative attributes and OpenRTB's creative attributes.
 */
public class CreativeAttributeMapper {
  private static ImmutableMultimap<CreativeAttribute, Integer> openrtbToDc =
      ImmutableMultimap.<CreativeAttribute, Integer>builder()
          // Empty mappings listed only for documentation
          .putAll(CreativeAttribute.AD_CAN_BE_SKIPPED, 44)
          .putAll(CreativeAttribute.ANNOYING)
          .putAll(CreativeAttribute.AUDIO_AUTO_PLAY)
          .putAll(CreativeAttribute.AUDIO_USER_INITIATED)
          .putAll(CreativeAttribute.EXPANDABLE_AUTOMATIC)
          .putAll(CreativeAttribute.EXPANDABLE_CLICK_INITIATED)
          .putAll(CreativeAttribute.EXPANDABLE_ROLLOVER_INITIATED, 28)
          .putAll(CreativeAttribute.HAS_AUDIO_ON_OFF_BUTTON)
          .putAll(CreativeAttribute.POP_UP)
          .putAll(CreativeAttribute.PROVOCATIVE_OR_SUGGESTIVE)
          .putAll(CreativeAttribute.SURVEYS)
          .putAll(CreativeAttribute.TEXT_ONLY, 1)
          .putAll(CreativeAttribute.USER_INTERACTIVE)
          .putAll(CreativeAttribute.VIDEO_IN_BANNER_AUTO_PLAY, 2, 22)
          .putAll(CreativeAttribute.VIDEO_IN_BANNER_USER_INITIATED, 2, 22)
          .putAll(CreativeAttribute.WINDOWS_DIALOG_OR_ALERT_STYLE)
      .build();
  private static ImmutableSet<CreativeAttribute>[] dcToOpenrtb = MapperUtil.multimapIntToSets(
      ImmutableMultimap.<Integer, CreativeAttribute>builder()
          .putAll(44, CreativeAttribute.AD_CAN_BE_SKIPPED)
          .putAll(28, CreativeAttribute.EXPANDABLE_ROLLOVER_INITIATED)
          .putAll(1, CreativeAttribute.TEXT_ONLY)
          .putAll(32, CreativeAttribute.USER_INTERACTIVE)
          .build());

  public static ImmutableCollection<CreativeAttribute> toOpenRtb(int dc) {
    return MapperUtil.get(dcToOpenrtb, dc);
  }

  public static ImmutableCollection<Integer> toDoubleClick(CreativeAttribute openrtb) {
    return openrtbToDc.get(openrtb);
  }

  public static ImmutableSet<CreativeAttribute> toOpenRtb(Collection<Integer> dcList) {
    ImmutableSet.Builder<CreativeAttribute> openrtbSet = ImmutableSet.builder();

    for (int dc : dcList) {
      openrtbSet.addAll(toOpenRtb(dc));
    }

    return openrtbSet.build();
  }

  public static ImmutableSet<Integer> toDoubleClick(Collection<CreativeAttribute> openrtbList) {
    ImmutableSet.Builder<Integer> dcSet = ImmutableSet.builder();

    for (CreativeAttribute openrtb : openrtbList) {
      dcSet.addAll(toDoubleClick(openrtb));
    }

    return dcSet.build();
  }
}
