package com.example.ktgk_2415053122205_phamquocdat

import java.io.Serializable

class KhoanChi_205(
    var maKhoanChi_205: String,
    var tenKhoanChi_205: String,
    var soTien_205: Double,
    var ghiChu_205: String,
    var daThanhToan_205: Boolean
) : Serializable {

    fun layThongTin_205(): String {
        val trangThai_205 = if (daThanhToan_205) "Đã thanh toán" else "Chưa thanh toán"
        return "Mã: $maKhoanChi_205\nTên: $tenKhoanChi_205\nSố tiền: $soTien_205\nGhi chú: $ghiChu_205\nTrạng thái: $trangThai_205"
    }

    fun laSoTienHopLe_205(): Boolean {
        return soTien_205 > 0
    }
}