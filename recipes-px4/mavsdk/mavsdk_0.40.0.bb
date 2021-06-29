DESCRIPTION = "API and library for MAVLink compatible systems written in C++17"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=b2988a8644dbb23689bbfb7ef6b0f1da"

SRCREV = "v0.40.0"
SRC_URI = "gitsm://git@github.com/mavlink/mavsdk.git;branch=main;protocol=https"

S = "${WORKDIR}/git"
PR = "r0"

DEPENDS = "curl jsoncpp libtinyxml2"

inherit cmake
