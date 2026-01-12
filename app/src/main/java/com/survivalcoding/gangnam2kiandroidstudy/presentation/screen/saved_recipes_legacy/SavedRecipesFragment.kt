package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes_legacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentSavedRecipesLegacyBinding

/**
 * SavedRecipesLegacyFragment
 *
 * 이 Fragment는 "저장된 레시피 목록 화면"을 담당하는 레거시 UI 컴포넌트이다.
 *
 * Activity는 이 Fragment를 담는 컨테이너 역할만 수행하고,
 * 실제 화면 구성과 UI 로직은 Fragment가 책임진다.
 *
 * 또한 RecyclerView 아이템 클릭에 대한
 * "최종 처리 주체" 역할도 Fragment가 담당한다.
 */
class SavedRecipesLegacyFragment :
    Fragment(),
    SavedRecipeClickListener {   // Adapter 클릭 이벤트를 받기 위해 Interface 구현

    /**
     * ViewBinding 객체
     *
     * - fragment_saved_recipes_legacy.xml을 기반으로
     *   빌드 시점에 자동 생성된 Binding 클래스이다.
     *
     * - _binding / binding 두 개로 나누는 이유:
     *   Fragment의 View 생명주기는 Fragment 자체 생명주기보다 짧기 때문에
     *   View가 파괴된 이후에도 binding을 참조하면 메모리 누수가 발생할 수 있다.
     */
    private var _binding: FragmentSavedRecipesLegacyBinding? = null

    /**
     * binding은 null이 아님을 보장하고 사용하기 위한 프로퍼티
     *
     * - View가 존재하는 구간(onCreateView ~ onDestroyView)에서만 접근해야 한다.
     * - !! 연산자를 사용하지만, 생명주기를 지켜 사용하면 안전하다.
     */
    private val binding get() = _binding!!

    /**
     * Fragment의 View를 생성하는 생명주기 메서드
     *
     * - XML 레이아웃을 inflate하여 View 객체를 생성한다.
     * - ViewBinding 객체도 이 시점에서 함께 초기화된다.
     *
     * 이 단계에서는 아직 View가 Fragment에 "붙기 전" 상태이므로
     *     UI 이벤트 처리나 데이터 바인딩은 하지 않는다.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedRecipesLegacyBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    /**
     * View가 완전히 생성된 이후 호출되는 메서드
     *
     * - RecyclerView 설정
     * - Adapter 연결
     * - 클릭 리스너 등록
     *
     * View를 실제로 사용하는 모든 코드는
     * 반드시 이 메서드 이후에 작성해야 한다.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         *
         *
         * Adapter를 생성할 때 Fragment 자신(this)을 전달한다.
         *
         * 이유:
         * - Adapter는 클릭을 감지하는 역할만 수행한다.
         * - "클릭 이후 무엇을 할지"는 Fragment가 결정해야 한다.
         *
         * → 책임 분리를 위해 Interface 구조를 사용한다.
         */
        binding.recyclerView.adapter =
            SavedRecipesLegacyAdapter(listener = this)
    }

    /**
     * Adapter에서 아이템이 클릭되었을 때 호출되는 메서드
     *
     * - 이 시점은 RecyclerView 아이템 클릭 직후이다.
     * - 어떤 동작을 할지는 Fragment가 자유롭게 결정한다.
     *
     * 예:
     * - Toast 표시
     * - 상세 화면 이동
     * - 로그 기록
     */
    override fun onRecipeClick(recipeTitle: String) {
        // 상세 화면 이동 or Toast 처리
    }

    /**
     * Fragment의 View가 파괴될 때 호출되는 메서드
     *
     * - Fragment 인스턴스는 남아 있을 수 있지만
     * - View는 이미 제거된 상태일 수 있다.
     *
     * 이때 binding이 View를 계속 참조하면
     * 메모리 누수(Leak)가 발생하므로
     * View 생명주기 종료 시점에 반드시 null 처리한다.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
