/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

package fr.dwightstudio.deepworld.common.utils;

import java.util.Arrays;

public enum MachineTier {
    WOOD(0),
    IRON(1),
    STEEL(2),
    OBSIDIAN_INFUSED_STEEL(3);

    private final int level;

    MachineTier(int level) {
        this.level = level;
    }

    public Iterable<MachineTier> getSameOrUpper() {
        return Arrays.stream(values()).filter(tier -> tier.level >= this.level).toList();
    }

    public Iterable<MachineTier> getSameOrLower() {
        return Arrays.stream(values()).filter(tier -> tier.level <= this.level).toList();
    }
}
