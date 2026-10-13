package com.example.ktgk_2415053122205.phamquocdat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtThongTinChiTiet_205: TextView
    private lateinit var btnSuaKhoanChi_205: Button
    private lateinit var btnQuayLai_205: Button
    private var khoanChi_205: KhoanChi_205? = null

    private val nhanKetQuaSua_205 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            khoanChi_205 = result.data?.getSerializableExtra("khoan_chi_da_sua") as? KhoanChi_205
            hienThiThongTin_205()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        supportActionBar?.title = "Phạm Quốc Đạt - 2415053122205 - Chi tiết khoản chi"

        txtThongTinChiTiet_205 = findViewById(R.id.txtThongTinChiTiet_205)
        btnSuaKhoanChi_205 = findViewById(R.id.btnSuaKhoanChi_205)
        btnQuayLai_205 = findViewById(R.id.btnQuayLai_205)

        khoanChi_205 = intent.getSerializableExtra("obj_khoan_chi") as? KhoanChi_205

        if (khoanChi_205 != null) {
            hienThiThongTin_205()
        } else {
            Toast.makeText(this, "Không nhận được dữ liệu hợp lệ!", Toast.LENGTH_SHORT).show()
        }

        btnSuaKhoanChi_205.setOnClickListener {
            val intentSua_205 = Intent(this, ChinhSuaChiTieuActivity::class.java)
            intentSua_205.putExtra("obj_sua", khoanChi_205)
            nhanKetQuaSua_205.launch(intentSua_205)
        }

        btnQuayLai_205.setOnClickListener {
            val intentTraVe_205 = Intent()
            intentTraVe_205.putExtra("khoan_chi_moi", khoanChi_205)
            setResult(RESULT_OK, intentTraVe_205)
            finish()
        }
    }

    private fun hienThiThongTin_205() {
        txtThongTinChiTiet_205.text = khoanChi_205?.layThongTin_205()
    }
}