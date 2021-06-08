DESCRIPTION = "Router for MAVLink packets between UDP, TCP or serial endpoints"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=93888867ace35ffec2c845ea90b2e16b"

SRCREV = "29ed3b74c0a885c8de32608e6882c26e6be8d201"
SRC_URI = "gitsm://git@github.com/mavlink-router/mavlink-router.git;protocol=https"

SRC_URI += "file://main.conf"
SRC_URI += "file://mavlink-router.sh"

S = "${WORKDIR}/git"
PR = "r0"

inherit autotools update-rc.d

INITSCRIPT_NAME = "mavlink-router"
INITSCRIPT_PARAMS = "defaults"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--enable-systemd --with-systemdsystemunitdir=${systemd_unitdir}/system/,--disable-systemd"

do_install_append () {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/mavlink-router

    install -m 0644 ${WORKDIR}/main.conf ${D}${sysconfdir}/mavlink-router/main.conf
    install -m 0755 ${WORKDIR}/mavlink-router.sh ${D}${sysconfdir}/init.d/mavlink-router
}
