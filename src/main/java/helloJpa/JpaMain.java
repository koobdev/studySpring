package helloJpa;

import helloJpa.domain.Member;
import org.h2.util.TempFileDeleter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{
            /*  // 회원 저장
            Member member = new Member();
            member.setId(2L);
            member.setName("helloB");
             */

            /*  // 회원 조회
            Member member = em.find(Member.class, 1L);
            System.out.println("member id = " + member.getId());
            System.out.println("member name = " + member.getName());


            // JPQL사용하여 조회
            List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            for (Member member : memberList) {
                System.out.println("member1 = " + member.getName());
            }
            */

            /*  // 회원 삭제
            Member member = em.find(Member.class, 1L);
            em.remove(member);
            */

            /*  // 회원 수정
            Member member = em.find(Member.class, 1L);
            member.setName("helloC");
            // em.persist(member)안해도 됨..!
             */


            // 비영속
//            Member member1 = new Member();
//            member1.setId(100L);
//            member1.setName("member100");

            // 영속
//            em.persist(member);

            // 준영속
//            em.detach(member);

            // 삭제
//            em.remove(member);


//            JpaTeam team = new JpaTeam();
//            team.setName("teamA");
//            em.persist(team);
//
//            JpaMember member = new JpaMember();
//            member.setUsername("memberA");
////            member.setTeamId(team.getId());
//            member.setJpateam(team); // 단방향 연관관계 시, set
//            em.persist(member);

            // 연관관계 없는 조회
            // 연관관계가 없어서 em.find를 2번해야지만 찾을 수 있다.
//            JpaMember findMember = em.find(JpaMember.class, 1L);
//            Long teamId = findMember.getTeamId();
//            JpaTeam findTeam = em.find(JpaTeam.class, teamId);


            // 단방향 연관관계 조회
//            JpaMember findMember = em.find(JpaMember.class, member.getId());
//            JpaTeam findTeam = findMember.getJpateam();
//            System.out.println("findTeam.getName() = " + findTeam.getName());



//            em.flush();
//            em.clear();



            // 양방향 연관관계
//            JpaMember findMember = em.find(JpaMember.class, member.getId());
//            List<JpaMember> members = findMember.getJpateam().getMembers();
//
//            for (JpaMember m : members) {
//                System.out.println("member name = " + m.getUsername());
//            }


            // 양방향 매핑 시 가장 많이 하는 실수
//            JpaTeam team = new JpaTeam();
//            team.setName("team1");
//            em.persist(team);
////
//            JpaMember member = new JpaMember();
//            member.setUsername("member1");
////            member.setJpateam(team);
//            team.addMember(member);
//
//            JpaMember member2 = new JpaMember();
//            member2.setUsername("member2");
//            member2.setJpateam(team);
////            team.addMember(member2);
//
//            em.persist(member);
//            em.persist(member2);


//            em.flush();
//            em.clear();

            JpaTeam team = new JpaTeam();
            team.setName("team1");
            em.persist(team);

            JpaMember member = new JpaMember();
            member.setUsername("member1");
            member.setJpateam(team);
            em.persist(member);

            em.flush();
            em.clear();

            JpaTeam findTeam = em.find(JpaTeam.class, team.getId());
            List<JpaMember> findMembers = findTeam.getMembers();

            for (JpaMember m : findMembers) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }


            ts.commit();
        }catch (Exception e){
            ts.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
