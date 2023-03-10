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

package fr.dwightstudio.deepworld.common.components;

public enum ComponentClass {
    // Classes
    PRIMARY(0),
    SECONDARY(1),
    TERTIARY(2),
    QUATERNARY(3),
    QUINARY(4),
    SENARY(5),
    SEPTENARY(6),
    OCTONARY(7);

    // Vars
    private int index;
    private int number;

    // Constructor
    ComponentClass(int index) {
        this.index = index;
        this.number = index + 1;
    }


    public int getIndex() {
        return index;
    }

    public int getNumber() {
        return number;
    }
}
