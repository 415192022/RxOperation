/*
 * Copyright (C) 2015 Tomás Ruiz-López.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.li.utils.ui.fablayout;

public class Point {

    protected float x, y;
    protected float control0X, control0Y;
    protected float control1X, control1Y;

    public Point(float control0X, float control0Y, float control1X, float control1Y, float x, float y) {
        this.control0X = control0X;
        this.control0Y = control0Y;
        this.control1X = control1X;
        this.control1Y = control1Y;
        this.x = x;
        this.y = y;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
